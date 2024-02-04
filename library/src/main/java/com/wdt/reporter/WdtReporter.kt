package com.wdt.reporter

import android.util.LruCache
import com.wdt.reporter.param.ParamParser
import com.wdt.reporter.param.Parameter
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy
import java.util.concurrent.Executors
import kotlin.reflect.KClass

object WdtReporter {

    internal var DEFAULT_REPORTER_CLASS: KClass<out IReporter>? = null
    private var PARAMETER_CACHE_LIMIT: Int = 50
    private var REPORTER_CACHE_LIMIT: Int = 10
    private var EVENT_CACHE_LIMIT: Int = 10
    private val executor by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        Executors.newFixedThreadPool(1)
    }

    /**
     * 设置默认的执行者
     */
    @JvmStatic
    fun setDefaultReporter(clazz: Class<out IReporter>) {
        DEFAULT_REPORTER_CLASS = clazz.kotlin
    }

    /**
     * 设置Reporter缓存数量
     */
    @JvmStatic
    fun setReporterCacheLimit(limit: Int) {
        REPORTER_CACHE_LIMIT = limit
    }

    /**
     * 设置Parameter缓存数量
     */
    @JvmStatic
    fun setParameterCacheLimit(limit: Int) {
        PARAMETER_CACHE_LIMIT = limit
    }

    /**
     * 设置Event缓存数量
     */
    @JvmStatic
    fun setEventCacheLimit(limit: Int) {
        EVENT_CACHE_LIMIT = limit
    }

    private val eventCache by lazy { LruCache<Class<*>, Any>(EVENT_CACHE_LIMIT) }
    private val parameterCache by lazy { LruCache<String, Parameter>(PARAMETER_CACHE_LIMIT) }
    private val reporterCache by lazy { LruCache<KClass<out IReporter>, IReporter>(REPORTER_CACHE_LIMIT) }

    /**
     * 为create方法补充缓存逻辑，是暴露给外部创建调用Event的唯一公开方法
     */
    @JvmStatic
    fun <T : Any> get(eventClazz: Class<T>): T {
        validateEventInterface(eventClazz)
        var event: T? = null
        val cache = eventCache[eventClazz]
        if (cache != null) {
            event = cache as? T
        }
        if (event == null) {
            synchronized(eventCache) {
                if (event == null) {
                    event = create(eventClazz)
                    eventCache.put(eventClazz, event)
                }
            }
        }
        return event ?: create(eventClazz)
    }

    /**
     * create proxy class
     */
    private fun <T : Any> create(event: Class<T>): T {
        validateEventInterface(event)
        return Proxy.newProxyInstance(
            event.classLoader,
            arrayOf(event),
            object : InvocationHandler {
                override fun invoke(proxy: Any?, method: Method?, args: Array<out Any?>?): Any? {
                    method ?: return null
                    // If the method is a method from Object then return null invocation.
                    if (method.declaringClass == Any::class.java) {
                        return null
                    }
                    executor.execute {
                        //Parsing parameters
                        val parameter: Parameter = loadParameter(method, args)
                        //Execution event
                        execute(parameter)
                    }
                    return null
                }
            }) as T
    }

    /**
     * 加载参数
     */
    private fun loadParameter(method: Method, args: Array<out Any?>?): Parameter {
        val key = createParamKey(method, args)
        var parameter = parameterCache[key]
        if (parameter != null) {
            return parameter
        }
        synchronized(parameterCache) {
            parameter = ParamParser.parse(method, args)
            parameterCache.put(key, parameter)
        }
        return parameter
    }

    private fun createParamKey(method: Method?, args: Array<out Any?>?): String {
        return "${method.hashCode()}_${args?.contentToString().hashCode()}"
    }

    /**
     * 执行Event上报
     */
    private fun execute(parameter: Parameter) {
        //创建IReport实例
        var instance = reporterCache[parameter.reporterClass]
        if (instance == null) {
            synchronized(reporterCache) {
                if (instance == null) {
                    instance = parameter.reporterClass.java.newInstance()
                    reporterCache.put(parameter.reporterClass, instance)
                }
            }
        }
        //执行report()
        instance.report(parameter)
    }

    private fun <T> validateEventInterface(event: Class<T>) {

        require(event.isInterface) { "API declarations must be interfaces." }

        // Prevent API interfaces from extending other interfaces. This not only avoids a bug in
        // Android (http://b.android.com/58753) but it forces composition of API declarations which is
        // the recommended pattern.
        require(event.interfaces.isEmpty()) { "API interfaces must not extend other interfaces." }
    }
}
