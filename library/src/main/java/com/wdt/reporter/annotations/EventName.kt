package com.wdt.reporter.annotations

/**
 * 声明Event名称/标志
 */
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class EventName(val name: String)