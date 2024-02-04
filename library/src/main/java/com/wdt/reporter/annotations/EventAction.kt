package com.wdt.reporter.annotations


/**
 * 声明Event行为
 */
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
annotation class EventAction(val action: String)