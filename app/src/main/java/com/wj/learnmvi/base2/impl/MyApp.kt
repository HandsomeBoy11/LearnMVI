package com.wj.learnmvi.base2.impl

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext

/**
 *  Author:WJ
 *  Date:2023/7/6 15:41
 *  Describe：
 */
class MyApp : Application() {
    private val modules = mutableListOf(main2Module)

    companion object {
        private lateinit var mApp: Application
        fun getInstance() = mApp
    }

    override fun onCreate() {
        super.onCreate()
        mApp = this
        initKoin()
    }

    private fun initKoin() {
        GlobalContext.startKoin {
            androidLogger()
            androidContext(this@MyApp)
            modules(modules)
        }
    }
}