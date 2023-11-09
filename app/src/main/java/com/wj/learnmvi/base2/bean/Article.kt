package com.wj.learnmvi.base2.bean

data class Article(
    var curPage: Int,
    val datas: List<ArticleItem>
)

data class ArticleItem(
    val title: String,
    val link: String,
    val userId: Int,
    val niceDate: String
)