package com.wj.learnmvi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_bottom.*
import kotlinx.android.synthetic.main.item_bottom_item_select.view.*

/**
 *  Author:WJ
 *  Date:2023/11/13 10:37
 *  Describe：
 */
class DragBottomActivity :AppCompatActivity() {
    private val list = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drag_bottom)
        initData()
        initView()
    }

    private fun initData() {
        for (i in 0..100){
            list.add("当前条目为：$i")
        }
    }

    private fun initView() {
//        brv.applyInit(this,object :SimpleRvAdapter<String>(){
//            override fun getSingleTypeLayout(): Int = R.layout.item_bottom_item_select
//            override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//                holder.itemView.tvText.text= list[position]
//            }
//
//        })
    }
}