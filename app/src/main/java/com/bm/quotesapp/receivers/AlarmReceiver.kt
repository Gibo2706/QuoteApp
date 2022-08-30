package com.bm.quotesapp.receivers

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.bm.quotesapp.data.QuotesResponse
import com.bm.quotesapp.listeners.QuotesResponseListener
import com.bm.quotesapp.managers.RequestManager
import com.bm.quotesapp.notifications.Notifications

class AlarmReceiver: BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        val notificationManager: NotificationManager = p0?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        p1.let {
            var content = ""
            var author = ""
            val listener: QuotesResponseListener = object : QuotesResponseListener {
                override fun onSuccess(response: QuotesResponse, message: String) {
                    Log.d("QuotesViewModel", "Data collected")
                    content = response.content.toString()
                    author = response.author.toString()
                }

                override fun onFailure(message: String) {
                    Log.e("MainActivity - QuoteResponse", message)
                }
            }
            RequestManager().getRandQuote(listener = listener)
            Notifications().showNotification(notificationManager, content, p0)
        }
    }

}