package com.wj.learnmvi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.wj.learnmvi.login.data.UserInfo
import kotlinx.android.synthetic.main.item_my.view.*

/**
 *  Author:WJ
 *  Date:2023/5/25 15:53
 *  Describe：
 */
class My2Adapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var list :MutableList<UserInfo>?=null
    private var listener: ((position: Int) -> Unit)? = null
    private var longListener: ((position: Int) -> Unit)? = null

    private class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_my, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemView = holder.itemView
        list?.let {
            val userInfo = it[position]
            println("更新了条目$position $userInfo")
            userInfo.apply {
                itemView.tvUserId.text = userId.toString()
                itemView.tvToken.text = token
            }
            itemView.setBackgroundColor(
                ContextCompat.getColor(
                    itemView.context,
                    if (userInfo.isSelect) {
                        R.color.purple_200
                    } else {
                        R.color.white
                    }
                )
            )
        }

        itemView.setOnClickListener {
            listener?.invoke(holder.adapterPosition)
//            setData(tempList)
        }
        itemView.setOnLongClickListener {
            longListener?.invoke(holder.adapterPosition)
            true
        }
    }

    override fun getItemCount(): Int = list?.size?:0


    fun setData(list: MutableList<UserInfo>?) {
        this.list=list
//        list?.let {
//            this.list.addAll(it)
//        }
        notifyDataSetChanged()
    }

    fun insertedData(index: Int, userInfo: UserInfo) {
        notifyItemInserted(index)
//        if (index <= this.list.size) {
//            this.list.add(index, userInfo)
//        }
    }

    fun reMoveData(index: Int) {
        notifyItemRemoved(index)
//        if (index < this.list.size) {
//            this.list.removeAt(index)
//        }
    }

    fun updateData(index: Int, userInfo: UserInfo) {
        notifyItemChanged(index)
//        if (index < this.list.size) {
//            this.list[index] = userInfo
//        }
    }

    fun notifyAllItem() {
        notifyDataSetChanged()
    }

    fun setItemClickListener(listener: (position: Int) -> Unit) {
        this.listener = listener
    }

    fun setItemLongClickListener(longListener: (position: Int) -> Unit) {
        this.longListener = longListener
    }
}