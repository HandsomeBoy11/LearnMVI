package com.wj.learnmvi.statusTest

import android.os.Bundle
import android.view.View
import com.gyf.barlibrary.ImmersionBar
import com.wj.learnmvi.R
import kotlinx.android.synthetic.main.fragment_status_three.*

/**
 *  Author:WJ
 *  Date:2023/11/16 14:26
 *  Describe：
 */
class ThreeFragment : BaseFragment() {
    override fun getLayoutId(): Int = R.layout.fragment_status_three
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        ImmersionBar.with(this).init()
        setDarkMode(true)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            setDarkMode(true)
        }
    }
}