package com.wj.learnmvi.base2.impl

import com.wj.learnmvi.base2.IUiState
import com.wj.learnmvi.base2.bean.Article
import com.wj.learnmvi.base2.bean.Banner


data class MainState(val bannerUiState: BannerUiState, val detailUiState: DetailUiState) : IUiState

sealed class BannerUiState {
    object INIT : BannerUiState()
    data class SUCCESS(val models: List<Banner>) : BannerUiState()
}

sealed class DetailUiState {
    object INIT : DetailUiState()
    data class SUCCESS(val articles: Article) : DetailUiState()
}