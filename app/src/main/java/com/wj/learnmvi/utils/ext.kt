package com.wj.learnmvi.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 *  Author:WJ
 *  Date:2023/5/26 14:14
 *  Describe：
 */
fun <T> MutableList<T>.swap(i1: Int, i2: Int) {
    val tmp = this[i1]
    this[i1] = this[i2]
    this[i2] = tmp
}

fun <T> MutableList<T>.moveToLast(i1: Int): MutableList<T> {
    val tmp = this[i1]
    this.remove(tmp)
    this.add(tmp)
    return this
}

fun RecyclerView.ViewHolder.setDiffItemClickListener(function: (position: Int) -> Unit) {
    this.itemView.tag = this
    this.itemView.setOnClickListener {
        if (it.tag is RecyclerView.ViewHolder) {
            val adapterPosition = (it.tag as RecyclerView.ViewHolder).adapterPosition
            function.invoke(adapterPosition)
        }
    }
}

fun View.setDiffItemClickListener(holder:RecyclerView.ViewHolder,function: (position: Int) -> Unit) {
    this.tag = holder
    this.setOnClickListener {
        if (it.tag is RecyclerView.ViewHolder) {
            val adapterPosition = (it.tag as RecyclerView.ViewHolder).adapterPosition
            function.invoke(adapterPosition)
        }
    }
}