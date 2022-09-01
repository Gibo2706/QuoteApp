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
import kotlinx.coroutines.*

class AlarmReceiver: BroadcastReceiver() {
    @OptIn(DelicateCoroutinesApi::class)
    override fun onReceive(p0: Context?, p1: Intent?) {
        val notificationManager: NotificationManager = p0?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        Notifications().initChannel(notificationManager)
        p1.let {
            var content = ""
            var author = ""

            GlobalScope.launch {
                suspend {
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
                    delay(5000)
                    withContext(Dispatchers.Main){
                        content.let { it1 -> Notifications().showNotification(notificationManager, it1, p0) }
                        Log.d("Notifications", "Notification sent, $content - $author")
                    }
                }.invoke()
            }
        }
    }

}