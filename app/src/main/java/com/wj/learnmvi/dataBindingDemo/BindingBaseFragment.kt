package com.wj.learnmvi.dataBindingDemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 *  Author:WJ
 *  Date:2024/6/25 10:32
 *  Describe：
 */
abstract class BindingBaseFragment<VM : ViewModel, VB : ViewBinding> : Fragment() {

    protected lateinit var viewModel: VM
    protected lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModelInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getViewBindingInstance(inflater, container!!)
        return binding.root
    }

    private fun getViewModelInstance(): VM {
        val superClass = javaClass.genericSuperclass as ParameterizedType
        val vmClass = (superClass.actualTypeArguments[0] as Class<VM>).kotlin
        return ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(vmClass.java)
    }


    private fun getViewBindingInstance(inflater: LayoutInflater, container: ViewGroup): VB {
        val superClass = javaClass.genericSuperclass as ParameterizedType
        val vbClass = superClass.actualTypeArguments[1] as Class<VB>
        val method = vbClass.getMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        )
        return method.invoke(null, inflater, container, false) as VB
    }
}