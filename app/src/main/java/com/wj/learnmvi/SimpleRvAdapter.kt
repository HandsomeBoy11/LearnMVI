package com.wj.learnmvi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 *  Author:WJ
 *  Date:2023/8/21 15:59
 *  Describe：
 */
abstract class SimpleRvAdapter<T> : RecyclerView.Adapter<SimpleRvAdapter.MyViewHolder>() {
    protected var dataList: MutableList<T>? = null
    protected var listener: ((i: Int) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(getSingleTypeLayout(), parent, false))
    }

    abstract fun getSingleTypeLayout(): Int

    override fun getItemCount(): Int = dataList?.size ?: 0

    fun setData(dataList: MutableList<T>?) {
        this.dataList = dataList
        notifyDataSetChanged()
    }

    fun getData(): MutableList<T>? = dataList

    fun setItemClickListener(listener: (i: Int) -> Unit) {
        this.listener = listener
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}