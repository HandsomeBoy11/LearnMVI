package com.wj.learnmvi.statusTest

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 *  Author:WJ
 *  Date:2023/11/16 17:07
 *  Describe：
 */
abstract class BaseFragment :Fragment() {
    private lateinit var mContext: Context
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(),null)
    }

    abstract fun getLayoutId(): Int
    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mContext = context
    }
    fun setDarkMode(isDarkMode: Boolean) {
        val window = requireActivity().window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (isDarkMode) {
                window.decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                window.decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            }
        }
    }
    fun getStatusView(statusViewColor: Int = Color.TRANSPARENT): View {
        return View(mContext)
    }
}