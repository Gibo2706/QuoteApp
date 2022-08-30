package com.bm.quotesapp.architecture.home

data class QuotesState(
    val content: String? = "",
    var author: String? = "",
    var offset: Float = 0f,
)
