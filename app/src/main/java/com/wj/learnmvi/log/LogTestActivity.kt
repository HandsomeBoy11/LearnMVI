package com.wj.learnmvi.log

import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.wj.learnmvi.R
import kotlinx.android.synthetic.main.activity_log_test.*
import kotlinx.coroutines.*
import java.math.BigDecimal

/**
 *  Author:WJ
 *  Date:2024/2/19 10:12
 *  Describe：
 */
class LogTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_test)
        btSave.setOnClickListener {
//            val logText = etInput.text.toString().trim()
//            LogHelper.logToFile(logText)
            doTest()
        }
        btRead.setOnClickListener {
//            tvShow.text = LogHelper.readLog()
            lifecycleScope.launch {
                val results = doRequest()
                println("wj==>$results")
            }
        }
    }

    private fun doTest() {
//        var index:Int=0
//        while (index<=10){
//            println("wj==>$index")
//            index++
//            if(index==3){
//                return
//            }
//        }
//        println("wj==> 是否还会继续")
//        println("wj==> 是否还会继续2")
//        println("wj==> 是否还会继续3")
        var value1:String? = null
        var value2 = ""
        if (TextUtils.isEmpty(value1) || value1!!.isBlank()) {
            value1 = "0"
        }
        if (TextUtils.isEmpty(value2) || value2.isBlank()) {
            value2 = "0"
        }
        val v1: BigDecimal = BigDecimal(value1)
        val v2: BigDecimal = BigDecimal(value2)
        v1.compareTo(v2)
    }

    private suspend fun fetchDataOne(): String {
        delay(3000)
        return "one"
    }

    private suspend fun fetchDataTwo(): String {
        delay(1000)
        return "two"
    }

    private suspend fun fetchDataThree(): String {
        delay(2000)
        return "three"
    }

    private suspend fun doRequest(): List<String> {
        val resultLists = mutableListOf<Deferred<String>>()
        coroutineScope {
            resultLists.add(async { fetchDataOne() })
            resultLists.add(async { fetchDataTwo() })
            resultLists.add(async { fetchDataThree() })
        }
        return resultLists.awaitAll()
    }
}