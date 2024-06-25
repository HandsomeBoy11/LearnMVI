package com.wj.learnmvi.dataBindingDemo

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 *  Author:WJ
 *  Date:2024/6/25 10:32
 *  Describe：
 * 如开启了混淆  需要添加混淆如下：
 *
 * # 保持ViewModel和ViewBinding不混淆，否则无法反射自动创建
 * -keep class * implements androidx.viewbinding.ViewBinding { *; }
 * -keep class * extends androidx.lifecycle.ViewModel { *; }
 *
 */
abstract class BindingBaseActivity <VM : ViewModel, VB : ViewBinding> : AppCompatActivity(){

    protected lateinit var viewModel: VM
    protected lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = getViewModelInstance()
        binding = getViewBindingInstance(layoutInflater)
        setContentView(binding.root)
    }

    private fun getViewModelInstance(): VM {
        val superClass = javaClass.genericSuperclass as ParameterizedType
        val vmClass = (superClass.actualTypeArguments[0] as Class<VM>).kotlin
        return ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(vmClass.java)
    }


    private fun getViewBindingInstance(inflater: LayoutInflater): VB {
        val superClass = javaClass.genericSuperclass as ParameterizedType
        val vbClass = superClass.actualTypeArguments[1] as Class<VB>
        val method = vbClass.getMethod("inflate", LayoutInflater::class.java)
        return method.invoke(null, inflater) as VB
    }
}