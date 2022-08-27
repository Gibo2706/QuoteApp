package com.bm.quotesapp.listeners

import com.bm.quotesapp.data.QuotesResponse

interface QuotesResponseListener {
    fun onSuccess(response: QuotesResponse, message: String)
    fun onFailure(message: String)
}