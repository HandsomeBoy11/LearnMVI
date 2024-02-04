package com.wj.learnmvi.statusTest

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.gyf.barlibrary.ImmersionBar
import com.wj.learnmvi.R
import kotlinx.android.synthetic.main.fragment_status_one.*

/**
 *  Author:WJ
 *  Date:2023/11/16 14:26
 *  Describe：
 */
class OneFragment : BaseFragment() {

    override fun getLayoutId(): Int =R.layout.fragment_status_one

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDarkMode(false)
    }

    override fun onResume() {
        super.onResume()
        ImmersionBar.with(this).keyboardEnable(true).init()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if(!hidden){
            ImmersionBar.with(this).keyboardEnable(true).init()
            setDarkMode(false)
        }
    }

}