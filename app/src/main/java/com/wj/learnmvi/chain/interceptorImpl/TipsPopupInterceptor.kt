package com.wj.learnmvi.chain.interceptorImpl

import androidx.appcompat.app.AlertDialog
import com.wj.learnmvi.chain.Interceptor
import com.wj.learnmvi.chain.InterceptorChain

/**
 *  Author:WJ
 *  Date:2024/9/3 13:57
 *  Describe：
 */
class TipsPopupInterceptor: Interceptor {
    override fun intercept(chain: InterceptorChain) {
        val context = chain.context
        val dialog = AlertDialog.Builder(context)
            .setTitle("Tip")
            .setMessage("Did you know you can do X, Y, Z?")
            .setPositiveButton("Got it") { _, _ ->
                chain.callBack("Got it")
            }
            .create()

        dialog.show()
    }
}