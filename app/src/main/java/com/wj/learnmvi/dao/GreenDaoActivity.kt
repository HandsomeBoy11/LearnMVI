package com.wj.learnmvi.dao

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wdt.reporter.WdtReporter
import com.wj.learnmvi.R
import com.wj.learnmvi.dao.BurialPointManager.deleteAll
import com.wj.learnmvi.dao.BurialPointManager.deleteUserBtn
import com.wj.learnmvi.dao.BurialPointManager.findAll
import com.wj.learnmvi.point.Home
import com.wj.learnmvi.point.JavaReporter
import kotlinx.android.synthetic.main.activity_green_dao.*

/**
 *  Author:WJ
 *  Date:2024/2/1 10:07
 *  Describe：
 */
class GreenDaoActivity : AppCompatActivity() {
    init {
        WdtReporter.setDefaultReporter(JavaReporter::class.java)
        WdtReporter.setParameterCacheLimit(100)
        WdtReporter.setReporterCacheLimit(5)
    }

    private val home = WdtReporter.get(Home::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_green_dao)
        tv1.setOnClickListener {
            for (i in 1..10) {
                addBtn("a", "a", "a", "a")
            }
        }
        tv2.setOnClickListener {
            addBtn("b", "a", "a", "a")
        }
        tv3.setOnClickListener {
            addBtn("a", "b", "a", "a")
        }
        tv4.setOnClickListener {
            addBtn("a", "a", "b", "a")
        }
        tv5.setOnClickListener {
            addBtn("a", "a", "a", "b")
        }
        // 查所有
        tv6.setOnClickListener {
            findAll()
        }
        tv7.setOnClickListener {
            deleteUserBtn("b")
        }
        tv8.setOnClickListener {
            deleteAll()
        }
    }

    private fun addBtn(userName: String, modName: String, pageName: String, btnName: String) {
        home.homePageMap(
            mapOf(
                "userName" to userName,
                "modName" to modName,
                "pageName" to pageName,
                "btnName" to btnName
            )
        )
    }


}