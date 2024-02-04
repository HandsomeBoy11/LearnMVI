package com.wdt.reporter.param

import com.wdt.reporter.IReporter
import kotlin.reflect.KClass

/**
 * Event参数
 */
class Parameter(

    /**
     * 事件名称
     */
    val eventName: String?,

    /**
     * 事件行为
     */
    val eventAction: String?,

    /**
     * 事件参数集合
     */
    val eventMap: HashMap<String, Any>,

    /**
     * 上报者类的Kotlin反射类
     */
    internal val reporterClass: KClass<out IReporter>

) {
    class Builder {
        var eventName: String? = ""
        var eventAction: String? = ""
        var eventMap: HashMap<String, Any> = HashMap()
        var reporterClass: KClass<out IReporter>? = null

        fun build(): Parameter {
            requireNotNull(reporterClass) {
                "The parameter of 'reporterClass' cannot be null," +
                        "It is recommended to use 'WBLiveReporter.setDefaultReport()' to set the default value."
            }
            return Parameter(eventName, eventAction, eventMap, reporterClass!!)
        }
    }
}