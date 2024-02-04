package com.wj.learnmvi.point

import com.wdt.reporter.IReporter
import com.wdt.reporter.param.Parameter
import com.wj.learnmvi.dao.BurialPointManager
import com.wj.learnmvi.utils.ExecutorUtils

class CustomReporter : IReporter {

    override fun report(parameter: Parameter) {

        d("custom event name -> ${parameter.eventName}")

        d("custom event action -> ${parameter.eventAction}")

        d("custom event map  -> ${parameter.eventMap}")
        val eventMap = parameter.eventMap
        ExecutorUtils.executeSync {
            BurialPointManager.addBtn(
                eventMap["userName"].toString(),
                eventMap["modName"].toString(),
                eventMap["pageName"].toString(),
                eventMap["btnName"].toString()
            )
        }
    }
}