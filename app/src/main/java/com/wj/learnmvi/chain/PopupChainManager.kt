package com.wj.learnmvi.chain

import android.content.Context
import com.wj.learnmvi.chain.interceptorImpl.TipsPopupInterceptor
import com.wj.learnmvi.chain.interceptorImpl.UpdatePopupInterceptor
import com.wj.learnmvi.chain.interceptorImpl.WelcomePopupInterceptor

/**
 *  Author:WJ
 *  Date:2024/9/3 13:58
 *  Describe：
 */
object PopupChainManager {
    private val interceptors = listOf(
        WelcomePopupInterceptor(),
        UpdatePopupInterceptor(),
        TipsPopupInterceptor()
    )

    fun handlePopups(context: Context,onResult:(msg:String)->Unit) {
        val chain = InterceptorChain(interceptors, 0, context,onResult)
        chain.proceed()
    }
}