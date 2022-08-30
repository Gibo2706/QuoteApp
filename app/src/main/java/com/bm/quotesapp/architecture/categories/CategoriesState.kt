package com.bm.quotesapp.architecture.categories

data class CategoriesState(
    val content: String? = "",
    val author: String? = "",
    val tags: Array<String> = emptyArray(),
)
