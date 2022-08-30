package com.bm.quotesapp.notifications

import android.R
import android.app.Notification
import android.app.NotificationManager
import android.content.Context

class Notifications {
    companion object {
        const val NOTIFICATION_CHANNEL_ID = "DailyQuotes"
        const val NOTIFICATION_CHANNEL_NAME = "Daily Quotes"
        const val NOTIFICATION_CHANNEL_DESCRIPTION = "Get your daily quote"
        const val NOTIFICATION_CHANNEL_IMPORTANCE = NotificationManager.IMPORTANCE_DEFAULT
    }

    fun initChannel(notificationManager: NotificationManager) {
        val channel = android.app.NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            NOTIFICATION_CHANNEL_NAME,
            NOTIFICATION_CHANNEL_IMPORTANCE
        )
        channel.description = NOTIFICATION_CHANNEL_DESCRIPTION
        notificationManager.createNotificationChannel(channel)
    }

    fun showNotification(notificationManager: NotificationManager, quote: String, mContext: Context) {
        val notification = Notification.Builder(mContext, NOTIFICATION_CHANNEL_ID)
            .setContentTitle("Daily Quote")
            .setContentText(quote)
            .setSmallIcon(R.drawable.ic_menu_share)
            .extend { builder ->
                builder.setStyle(
                    Notification.BigTextStyle()
                        .bigText(quote)
                )
            }
            .setAutoCancel(true)
            .build()
        notificationManager.notify(1, notification)
    }
}