package com.example.android.eggtimernotifications

import android.app.NotificationManager
import android.app.Service
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.android.eggtimernotifications.util.sendNotification

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)
        Log.d(TAG, "onMessageReceived: " + remoteMessage!!.from!!)
        remoteMessage?.data?.let {
            Log.d(TAG, "Message data payload: " + remoteMessage.data)
        }

        remoteMessage?.notification?.let {
            Log.d(TAG, "Message Notification Body: ${it.body}")
            sendNotification(it.body!!)
        }

    }

    private fun sendNotification(body: String) {
        val notificationManager = ContextCompat.getSystemService(
            applicationContext,
            NotificationManager::class.java
        ) as NotificationManager

        notificationManager.sendNotification(
           body,
            applicationContext
        )
    }

    override fun onNewToken(s: String?) {
        super.onNewToken(s)
        Log.d(TAG, "onNewToken: " + s!!)
    }

    companion object {

        private val TAG = MyFirebaseMessagingService::class.java.simpleName
    }


}
