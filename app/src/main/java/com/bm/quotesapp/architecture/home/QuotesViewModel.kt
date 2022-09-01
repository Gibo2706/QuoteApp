package com.bm.quotesapp.architecture.home

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bm.quotesapp.architecture.UIAction
import com.bm.quotesapp.data.QuotesResponse
import com.bm.quotesapp.listeners.QuotesResponseListener
import com.bm.quotesapp.managers.RequestManager
import com.bm.quotesapp.receivers.AlarmReceiver

private const val HALF_DAY = 43200000L

class QuotesViewModel : ViewModel() {
    var state by mutableStateOf(QuotesState())
        private set
    var status = MutableLiveData<Boolean?>()
    var statusNot = MutableLiveData<Boolean?>()

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
            is UIAction.TopBarClicked -> {
                statusNot.value = false
                // Notifications
                val alarmManager = action.mContext.getSystemService(ComponentActivity.ALARM_SERVICE) as AlarmManager
                val intent = Intent(action.mContext, AlarmReceiver::class.java)

                val flags = PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
                val pendingIntent = PendingIntent.getBroadcast(action.mContext, 0, intent, flags)
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), HALF_DAY, pendingIntent)

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