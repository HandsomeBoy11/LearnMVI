package com.wj.learnmvi.dataBindingDemo

import android.os.Bundle
import android.view.View
import com.wj.learnmvi.databinding.FragmentFristDbBinding

/**
 *  Author:WJ
 *  Date:2024/6/25 11:22
 *  Describe：
 */
class FirstDbFragment : BindingBaseFragment<FirstViewModel, FragmentFristDbBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvFragment.apply {
            setOnClickListener {
                text = viewModel.addCount()
            }
        }
    }
}