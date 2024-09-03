package com.wj.learnmvi.chain

/**
 *  Author:WJ
 *  Date:2024/9/3 13:50
 *  Describe：
 */
interface Interceptor {
    fun intercept(chain: InterceptorChain)
}