package com.wj.learnmvi.base2.impl

import com.wj.learnmvi.base2.BaseApi
import com.wj.learnmvi.base2.BaseData
import com.wj.learnmvi.base2.bean.Article
import com.wj.learnmvi.base2.bean.Banner
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *  Author:WJ
 *  Date:2023/7/6 15:29
 *  Describe：
 */
interface Main2Api:BaseApi {
    @GET("banner/json")
    suspend fun getBanner(): BaseData<List<Banner>>

    //页码从0开始
    @GET("article/list/{page}/json")
    suspend fun getArticle(@Path("page") page: Int): BaseData<Article>
}