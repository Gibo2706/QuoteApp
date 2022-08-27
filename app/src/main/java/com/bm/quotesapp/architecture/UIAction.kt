package com.bm.quotesapp.architecture

import android.content.Context

sealed class UIAction{
    object SwipeForNewQuote : UIAction()
    data class ShareQuote(val mContext: Context) : UIAction()


}
