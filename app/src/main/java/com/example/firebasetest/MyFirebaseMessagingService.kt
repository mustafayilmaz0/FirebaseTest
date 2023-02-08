package com.example.firebasetest

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        println("gelen-MyFirebaseMessagingService message-title: ${message.notification?.title}")
        println("gelen-MyFirebaseMessagingService message-body: ${message.notification?.body}")

        getNotification(baseContext, title = message.notification?.title, text = message.notification?.body)
    }

    fun getNotification(ctx: Context, title: String?, text: String?) {
        val b = NotificationCompat.Builder(ctx)

        b.setAutoCancel(true)
            .setDefaults(Notification.DEFAULT_ALL)
            .setWhen(System.currentTimeMillis())
            .setSmallIcon(android.R.drawable.ic_btn_speak_now)
            .setTicker("PushNotificationApp")
            .setContentTitle(title)
            .setContentText(text)
            .setDefaults(Notification.DEFAULT_LIGHTS or Notification.DEFAULT_SOUND)
            .setContentInfo("Info")

        val mNotificationManager = ctx.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        mNotificationManager.notify(999, b.build())
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        println("gelen-MyFirebaseMessagingService token: $token")
    }
}