package com.wj.learnmvi.chain

import android.content.Context

/**
 *  Author:WJ
 *  Date:2024/9/3 13:49
 *  Describe：
 */
class InterceptorChain(
    private val interceptors: List<Interceptor>,
    private val index: Int,
    val context: Context,
    val callBack:(msg:String)->Unit
) {
    fun proceed() {
        if (index < interceptors.size) {
            val nextChain = InterceptorChain(interceptors, index + 1, context,callBack)
            val interceptor = interceptors[index]
            interceptor.intercept(nextChain)
        }
    }
}