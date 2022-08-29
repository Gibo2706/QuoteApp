package com.bm.quotesapp.architecture

import android.content.Intent
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bm.quotesapp.data.QuotesResponse
import com.bm.quotesapp.listeners.QuotesResponseListener
import com.bm.quotesapp.managers.RequestManager


class QuotesViewModel : ViewModel() {
    var state by mutableStateOf(QuotesState())
        private set
    var status = MutableLiveData<Boolean?>()

    init {
         getData()
    }


    private fun getData(){
        val listener: QuotesResponseListener = object : QuotesResponseListener {
            override fun onSuccess(response: QuotesResponse, message: String) {
                Log.d("QuotesViewModel", "Data collected")
                state = state.copy(
                    content = response.content,
                    author = response.author
                )
            }

            override fun onFailure(message: String) {
                Log.e("MainActivity - QuoteResponse", message)
            }
        }
        for (i in 0..5)
            RequestManager().getRandQuote(listener = listener)
    }

    fun onAction(action: UIAction){
        when(action) {
            is UIAction.SwipeForNewQuote -> {
                status.value = true
                getData()
            }
            is UIAction.ShareQuote -> {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, state.content)
                action.mContext.startActivity(Intent.createChooser(shareIntent, "Share using"))
            }
            else -> {
                Log.e("QuotesViewModel", "Unknown action")
            }
        }
    }

    /*fun onDataChange(content: String?, author: String?){
        state = state.copy(
            content = content,
            author = author
        )
    }*/

}