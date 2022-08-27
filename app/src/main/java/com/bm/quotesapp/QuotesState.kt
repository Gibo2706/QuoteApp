package com.bm.quotesapp

data class QuotesState(
    val content: String? = "",
    var author: String? = "",
    var offset: Float = 0f,
)
