package com.wj.learnmvi.chain.interceptorImpl

import androidx.appcompat.app.AlertDialog
import com.wj.learnmvi.chain.Interceptor
import com.wj.learnmvi.chain.InterceptorChain

/**
 *  Author:WJ
 *  Date:2024/9/3 13:56
 *  Describe：
 */
class UpdatePopupInterceptor : Interceptor {
    override fun intercept(chain: InterceptorChain) {
        val context = chain.context
        val dialog = AlertDialog.Builder(context)
            .setTitle("Update")
            .setMessage("A new update is available.")
            .setPositiveButton("Update") { _, _ ->
                chain.proceed()
            }
            .setNegativeButton("Later") { _, _ ->
                chain.callBack("稍后更新")
            }
            .create()

        dialog.show()
    }
}