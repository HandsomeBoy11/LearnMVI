package com.wdt.reporter.annotations


/**
 * 声明Event参数集合
 * 用于标记固定格式的Json（Must be String）
 *
 * 例子：
 * {
 *  "productId": "327539590412623872",
 *  "name": "泉灵的语文课三年级上-测试",
 *  "type": "click",
 *  "imgUrl": "https://coolcdn.igetcool.com/t/20190520/18b382cbe01b5085209671e0a3e3999e.jpg",
 *  "subhead": "泉灵的语文课三年级上-测试"
 * }
 *
 */
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class EventParamExt