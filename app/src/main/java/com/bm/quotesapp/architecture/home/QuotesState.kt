package com.bm.quotesapp.architecture.home

data class QuotesState(
    val content: String? = "Let the future tell the truth and evaluate each one according to his work and accomplishments. The present is theirs; the future, for which I have really worked, is mine.",
    var author: String? = "Nikola Tesla",
    var offset: Float = 0f,
)
