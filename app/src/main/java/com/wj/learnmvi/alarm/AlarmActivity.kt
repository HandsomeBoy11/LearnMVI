package com.wj.learnmvi.alarm

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 *  Author:WJ
 *  Date:2023/11/27 10:38
 *  Describe：
 */
class AlarmActivity : AppCompatActivity() {
    val channel = Channel<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startService(Intent(this, MyService::class.java))
        println("wj==> 我开始了")

        val launch = lifecycleScope.launch {
            channel.receiveAsFlow().collect { str ->
                println("wj==> str = $str")
                if (func1()) {
                    println("wj==> 打印是否完成:  ${isFinish(str)}")
                } else {
                    println("wj==>未成功")
                }
            }
        }
        lifecycleScope.launch {
            for (i in 1..5) {
                channel.send("内容$i")
            }
        }
    }

    private suspend fun func1(): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                delay(5000)
                println("wj==>func1 ThreadName= ${Thread.currentThread().name}")
                true
            } catch (e: Exception) {
                false
            }
        }

    }

    private suspend fun isFinish(str: String): Boolean {
        return try {
            delay(2000)
            println("wj==>isFinish 打印中: $str  ThreadName= ${Thread.currentThread().name}")
            true
        } catch (e: Exception) {
            false
        }
    }
}