package com.wj.learnmvi.testFragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment

/**
 *  Author:WJ
 *  Date:2023/6/1 10:18
 *  Describe：
 */
open class BaseFragment : Fragment() {
    protected lateinit var mContext: Context
    protected lateinit var mActivity: Activity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mContext = context
        if (context is Activity) {
            this.mActivity = context
        }
    }

    fun pushFragment(fragment: Fragment) {
        if (mContext is TestFragmentActivity) {
            (mContext as TestFragmentActivity).pushFragment(fragment)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("wj==>Fragment","onCreate")
        if(savedInstanceState ==null){
            Log.e("wj==>Fragment","savedInstanceState ==null ${this::class.java.simpleName}")
        }else{
            Log.e("wj==>Fragment","savedInstanceState !=null${this::class.java.simpleName}")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("wj==>Fragment","onDestroyView ${this::class.java.simpleName}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("wj==>Fragment","onDestroy ${this::class.java.simpleName}")
    }
}