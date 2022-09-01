package com.bm.quotesapp.architecture

import android.content.Context

sealed class UIAction{
    // Home
    object SwipeForNewQuote : UIAction()
    data class ShareQuote(val mContext: Context) : UIAction()

    // Categories
    data class CategorySelected(val mCategory: String) : UIAction()

    // TopBar
    data class TopBarClicked(val mContext: Context) : UIAction()



}
