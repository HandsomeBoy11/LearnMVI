package com.wj.learnmvi.testFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.wj.learnmvi.R
import kotlinx.android.synthetic.main.fragment_one.*

/**
 *  Author:WJ
 *  Date:2023/6/1 10:07
 *  Describe：
 */
class OneFragment : BaseFragment() {

    companion object {
        const val PAGE_NAME :String ="pageName"
        fun getInstance(args: Bundle?): Fragment {
            val oneFragment = OneFragment()
            oneFragment.arguments = args ?: Bundle()
            return oneFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_one, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            tvPageName.text =it.getString(PAGE_NAME,"没名字")
        }
        tvNextPage.setOnClickListener {
           val f= TwoFragment.getInstance(bundleOf(PAGE_NAME to "第二页面"))
            (f as TwoFragment) .pageName ="第二页面"
            pushFragment(f)
        }
    }
}