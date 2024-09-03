package com.wj.learnmvi.chain.interceptorImpl

import androidx.appcompat.app.AlertDialog
import com.wj.learnmvi.chain.Interceptor
import com.wj.learnmvi.chain.InterceptorChain

/**
 *  Author:WJ
 *  Date:2024/9/3 13:53
 *  Describe：
 */
class WelcomePopupInterceptor : Interceptor {
    override fun intercept(chain: InterceptorChain) {
        val context = chain.context
        val dialog = AlertDialog.Builder(context)
            .setTitle("Welcome")
            .setMessage("Welcome to our app!")
            .setPositiveButton("OK") { _, _ ->
                chain.proceed()
            }
            .create()
        dialog.show()
    }
}