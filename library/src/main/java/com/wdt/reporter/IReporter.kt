package com.wdt.reporter

import com.wdt.reporter.param.Parameter


/**
 * 上报Event行为的执行者
 */
interface IReporter {

    /**
     * 上报Event
     * @param parameter 事件的参数集合
     */
    fun report(parameter: Parameter)

}