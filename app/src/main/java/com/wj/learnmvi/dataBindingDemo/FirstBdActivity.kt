package com.wj.learnmvi.dataBindingDemo

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.wj.learnmvi.R
import com.wj.learnmvi.databinding.ActivityFirstBdBinding
import com.wj.learnmvi.utils.dp

/**
 *  Author:WJ
 *  Date:2024/6/25 10:37
 *  Describe：
 */
class FirstBdActivity : BindingBaseActivity<FirstViewModel, ActivityFirstBdBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.tv1.setOnClickListener {
            binding.tv1.text = viewModel.addCount()
        }
        binding.rv.apply {
            layoutManager =LinearLayoutManager(this@FirstBdActivity)
            adapter = FirstAdapter()
            addItemDecoration(object :ItemDecoration(){
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    super.getItemOffsets(outRect, view, parent, state)
                    outRect.bottom =8.dp

                }
            })
        }
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.replace(R.id.flContainer,FirstDbFragment())
        beginTransaction.commit()

    }
}