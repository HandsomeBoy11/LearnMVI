package com.wj.learnmvi.base2.impl

import com.wj.learnmvi.base2.BaseViewModel
import com.wj.learnmvi.base2.IUiIntent

/**
 *  Author:WJ
 *  Date:2023/7/6 15:23
 *  Describe：
 */
class Main2ViewModel(private val homeRepo: Main2Repository) :BaseViewModel<MainState,MainIntent>() {
    override fun initUiState(): MainState =MainState(BannerUiState.INIT, DetailUiState.INIT)

    override fun handleIntent(intent: IUiIntent) {
        when (intent) {
            MainIntent.GetBanner -> {
                requestDataWithFlow(showLoading = true,
                    request = { homeRepo.requestWanData() },
                    successCallback = { data ->
                        sendUiState {
                            copy(
                                bannerUiState = BannerUiState.SUCCESS(
                                    data
                                )
                            )
                        }
                    },
                    failCallback = {})
            }
            is MainIntent.GetDetail -> {
                requestDataWithFlow(showLoading = false,
                    request = { homeRepo.requestRankData(intent.page) },
                    successCallback = { data ->
                        sendUiState {
                            copy(
                                detailUiState = DetailUiState.SUCCESS(
                                    data
                                )
                            )
                        }
                    })
            }
        }
    }
}