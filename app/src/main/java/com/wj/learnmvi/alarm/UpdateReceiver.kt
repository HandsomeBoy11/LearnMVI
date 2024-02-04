package com.wj.learnmvi.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

/**
 *  Author:WJ
 *  Date:2023/11/27 10:46
 *  Describe：
 */
class UpdateReceiver :BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        println("wj==> 我接收到了")

    }
}