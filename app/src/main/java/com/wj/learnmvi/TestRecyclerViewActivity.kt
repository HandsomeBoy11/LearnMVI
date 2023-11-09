package com.wj.learnmvi

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.wj.learnmvi.login.data.UserInfo
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

/**
 *  Author:WJ
 *  Date:2023/6/21 16:18
 *  Describe：
 */
class TestRecyclerViewActivity : AppCompatActivity() {
    private val my2Adapter by lazy { My2Adapter() }
    private val list = mutableListOf<UserInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initVeiw()
    }

    private fun initVeiw() {
        rvList.apply {
            layoutManager = LinearLayoutManager(this@TestRecyclerViewActivity)
            adapter = my2Adapter
        }
        for (i in 1..3) {
            val userInfo = UserInfo(i, "当前Token：$i")
            list.add(userInfo)
        }
        my2Adapter.setData(list)
        my2Adapter.setItemClickListener {
            Toast.makeText(this, "当前索引$it", Toast.LENGTH_SHORT).show()
        }
        ivFindData.setOnClickListener {
//            list.removeAt(10)
//            my2Adapter.setData(list)
//            rvList.scrollToPosition(0)
            my2Adapter.notifyAllItem()
            println("wj==> list: $list")
        }
        // 增
        tvUpdateItem.setOnClickListener {
            val index =
                if (etIndex.text.toString().isEmpty()) 0 else etIndex.text.toString().toInt()
            val userInfo = UserInfo(index, "新增Token：$index")
            if (index <= list.size) {
                list.add(index, userInfo)
                my2Adapter.notifyItemInserted(index)
            }
        }
        // 删
        tvAddData.setOnClickListener {
            val index =
                if (etIndex.text.toString().isEmpty()) 0 else etIndex.text.toString().toInt()
            if (index < list.size) {
                list.removeAt(index)
                my2Adapter.notifyItemRemoved(index)
            }
        }
        // 改
        tvChangePosition.setOnClickListener {
            val index =
                if (etIndex.text.toString().isEmpty()) 0 else etIndex.text.toString().toInt()
            val userInfo = UserInfo(index, "修改Token：$index")
            if (index < list.size) {
                list[index] = userInfo
                my2Adapter.notifyItemChanged(index)
            }
        }
        tvMove.setOnClickListener {
            val index =
                if (etIndex.text.toString().isEmpty()) 0 else etIndex.text.toString().toInt()
            if (index < list.size) {
                Collections.swap(list, index, list.size - 1)
                my2Adapter.notifyItemMoved(index, list.size - 1)
            }
        }
    }
}