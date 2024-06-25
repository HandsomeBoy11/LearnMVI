package com.wj.learnmvi.dataBindingDemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.wj.learnmvi.R
import com.wj.learnmvi.databinding.ItemForFirstBinding

/**
 *  Author:WJ
 *  Date:2024/6/25 10:50
 *  Describe：
 */
class FirstAdapter : RecyclerView.Adapter<FirstAdapter.MyViewHolder>() {
    inner class MyViewHolder(binding: ItemForFirstBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var binding: ItemForFirstBinding;

        init {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val binding: ItemForFirstBinding = DataBindingUtil.inflate(
            LayoutInflater.from(p0.context),
            R.layout.item_for_first,
            p0,
            false
        )
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = 3

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        p0.binding.tvItem.text = "条目$p1"
    }
}