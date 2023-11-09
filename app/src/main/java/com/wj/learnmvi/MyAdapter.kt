package com.wj.learnmvi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wj.learnmvi.login.data.UserInfo
import com.wj.learnmvi.utils.setDiffItemClickListener
import kotlinx.android.synthetic.main.item_my.view.*

/**
 *  Author:WJ
 *  Date:2023/5/25 15:53
 *  Describe：
 */
class MyAdapter : ListAdapter<UserInfo, MyAdapter.MyViewHolder>(MyCallback()) {

    private var listener: ((position: Int) -> Unit)? = null

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_my, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val userInfo = currentList[position]
        val itemView = holder.itemView
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
        holder.setDiffItemClickListener {
            listener?.invoke(it)
        }
    }

    override fun getItemCount(): Int = currentList.size
    /* override fun getItemId(position: Int): Long {
         return position.toLong()
     }*/

    fun getList(): MutableList<UserInfo> = currentList

    fun setData(list: MutableList<UserInfo>?) {
        submitList(list)
    }

    fun setItemClickListener(listener: (position: Int) -> Unit) {
        this.listener = listener
    }

    class MyCallback : DiffUtil.ItemCallback<UserInfo>() {
        override fun areItemsTheSame(
            oldItem: UserInfo,
            newItem: UserInfo
        ): Boolean {
            return oldItem.userId == newItem.userId
        }

        override fun areContentsTheSame(
            oldItem: UserInfo,
            newItem: UserInfo
        ): Boolean {
            return oldItem == newItem
        }
    }

}