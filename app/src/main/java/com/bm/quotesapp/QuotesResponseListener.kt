package com.bm.quotesapp

interface QuotesResponseListener {
    fun onSuccess(response: QuotesResponse, message: String)
    fun onFailure(message: String)
}