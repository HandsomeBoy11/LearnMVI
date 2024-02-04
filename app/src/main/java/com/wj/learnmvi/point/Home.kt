package com.wj.learnmvi.point

import com.wdt.reporter.annotations.*

@EventName("home")
@EventAction("class")
@EventReporter(CustomReporter::class)
interface Home {

    fun homePage(
        @EventParam("from_page") from: String,
        @EventParam("event_type") type: String = "page",
        @EventParam("page_name") pageName: String = "首页",
        @EventParam("name") name: String = "首页"
    )

    @EventAction("进入首页的事件-map")
    fun homePageMap(@EventParamMap map: Map<String, Any>)

    @EventAction("进入首页的事件-json")
    fun homePageJson(@EventParamExt json: String)


    @EventAction("进入首页的事件-multi")
    fun homePageMulti(
        @EventParam("from_page") from: String,
        @EventParamExt json: String,
        @EventParamMap map: Map<String, Any>
    )


    @EventAction("测试错误Json数据")
    fun testErrorJson(@EventParamExt value: Int)


    @EventAction("测试错误Map数据")
    fun testErrorMap(@EventParamMap map: Map<Boolean,String>)

}
