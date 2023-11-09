package com.wj.learnmvi.base2.impl

import com.wj.learnmvi.base2.WanRetrofitClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 *  Author:WJ
 *  Date:2023/7/6 15:42
 *  Describe：
 */
val main2Module = module {
    single { WanRetrofitClient.getService(Main2Api::class.java) }
    single { Main2Repository(get()) }
    viewModel { Main2ViewModel(get()) }
}