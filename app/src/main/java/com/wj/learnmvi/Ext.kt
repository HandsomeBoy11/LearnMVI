package com.wj.learnmvi

import android.content.Context
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 *  Author:WJ
 *  Date:2023/11/13 10:44
 *  Describe：
 */
/**
 * recyclerView初始化
 */
fun <T : RecyclerView.Adapter<out RecyclerView.ViewHolder>> RecyclerView.applyInit(context: Context,
                                                                                   rvAdapter: T,
                                                                                   rvLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context),
                                                                                   itemDecoration: RecyclerView.ItemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL),
                                                                                   adapterFunc: T.() -> Unit = {}) {
    layoutManager = rvLayoutManager
    addItemDecoration(itemDecoration)
    adapter = rvAdapter
    rvAdapter.adapterFunc()
}