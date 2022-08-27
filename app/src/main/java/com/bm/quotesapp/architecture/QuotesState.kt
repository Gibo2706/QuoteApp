package com.bm.quotesapp.architecture

data class QuotesState(
    val content: String? = "",
    var author: String? = "",
    var offset: Float = 0f,
)
