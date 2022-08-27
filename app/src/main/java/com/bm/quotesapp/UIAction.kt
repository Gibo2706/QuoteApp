package com.bm.quotesapp

import android.content.Context

sealed class UIAction{
    object swipeForNewQuote : UIAction()
    data class shareQuote(val mContext: Context) : UIAction()
    object shareQuoteWithAuthor : UIAction()


}
