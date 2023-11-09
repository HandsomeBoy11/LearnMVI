package com.wj.learnmvi.base2.impl

import com.wj.learnmvi.base2.BaseApi
import com.wj.learnmvi.base2.BaseData
import com.wj.learnmvi.base2.BaseRepository
import com.wj.learnmvi.base2.bean.Article
import com.wj.learnmvi.base2.bean.Banner

/**
 *  Author:WJ
 *  Date:2023/7/6 15:22
 *  Describe：
 */
class Main2Repository(private val api: Main2Api) :BaseRepository(){
    suspend fun requestWanData(): BaseData<List<Banner>> {
        return executeRequest { api.getBanner() }
    }

    suspend fun requestRankData(page: Int): BaseData<Article> {
        return executeRequest { api.getArticle(page) }
    }
}