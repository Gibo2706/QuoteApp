package com.bm.quotesapp.architecture.categories

import android.content.Intent
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bm.quotesapp.architecture.UIAction
import com.bm.quotesapp.data.QuotesResponse
import com.bm.quotesapp.listeners.QuotesResponseListener
import com.bm.quotesapp.managers.RequestManager

class CategoriesViewModel: ViewModel() {
    var catState by mutableStateOf(CategoriesState())
        private set


    init {
        getData()
    }
    private fun getData(){
        val listener: QuotesResponseListener = object : QuotesResponseListener {
            override fun onSuccess(response: QuotesResponse, message: String) {
                Log.d("QuotesViewModel", "Data collected")
                catState = catState.copy(
                    content = response.content,
                    author = response.author,
                    tags = response.tags,
                )
            }

            override fun onFailure(message: String) {
                Log.e("MainActivity - QuoteResponse", message)
            }
        }
        RequestManager().getRandQuote(listener = listener)
    }
    private fun getSpecQuote(tags:String){
        val listener: QuotesResponseListener = object : QuotesResponseListener {
            override fun onSuccess(response: QuotesResponse, message: String) {
                Log.d("QuotesViewModel", "Data collected")
                catState = catState.copy(
                    content = response.content,
                    author = response.author,
                    tags = response.tags,
                )
            }

            override fun onFailure(message: String) {
                Log.e("MainActivity - QuoteResponse", message)
            }
        }
        when(tags){
            "love" -> RequestManager().getLoveQuote(listener = listener)
            "life" -> RequestManager().getLifeQuote(listener = listener)
            "inspire" -> RequestManager().getInspirationalQuote(listener = listener)
            "motivational" -> RequestManager().getMotivationalQuote(listener = listener)
            "tech" -> RequestManager().getTechQuote(listener = listener)
        }
    }
    fun onAction(action: UIAction){
        when(action){
            is UIAction.CategorySelected -> {
                when(action.mCategory){
                    "Love" -> {
                        getSpecQuote("love")
                    }
                    "Inspirational" -> {
                        getSpecQuote("inspire")
                    }
                    "Motivational" -> {
                        getSpecQuote("motivational")
                    }
                    "Famous" -> {
                        getSpecQuote("famous")
                    }
                    "Technology" -> {
                        getSpecQuote("tech")
                    }
                    "Life" -> {
                        getSpecQuote("life")
                    }
                    "Funny" -> {
                        getSpecQuote("funny")
                    }

                }
            }
            is UIAction.ShareQuote -> {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, catState.content)
                action.mContext.startActivity(Intent.createChooser(shareIntent, "Share using"))
            }
            else -> {
                Log.e("CategoriesViewModel", "Unknown action")
            }
        }

    }
}