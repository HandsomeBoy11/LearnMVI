package com.wj.learnmvi.statusTest

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.gyf.barlibrary.ImmersionBar
import com.wj.learnmvi.R
import kotlinx.android.synthetic.main.fragment_status_two.*

/**
 *  Author:WJ
 *  Date:2023/11/16 14:26
 *  Describe：
 */
class TwoFragment : BaseFragment() {
    private lateinit var mContext: Context
    override fun getLayoutId(): Int = R.layout.fragment_status_two
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDarkMode(false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mContext = context
    }

    override fun onResume() {
        super.onResume()
        ImmersionBar.with(this).init()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            setDarkMode(false)
        }
    }

}