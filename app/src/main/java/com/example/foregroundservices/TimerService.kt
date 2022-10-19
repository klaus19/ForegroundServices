package com.example.foregroundservices

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.foregroundservices.Constants.Companion.channelID
import com.example.foregroundservices.Constants.Companion.foregroundServiceNotificationTitle
import com.google.android.material.R

class TimerService:Service() {

    override fun onCreate() {
        super.onCreate()
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val input = intent?.getStringExtra(Constants.inputExtra)
        val notificationIntent = Intent(this, MainActivity::class.java)

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.S){
            val pendingIntent = PendingIntent.getActivity(this,0,notificationIntent,0)
            getNotification(input, pendingIntent)
        }else{
            val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)
            getNotification(input, pendingIntent)
        }

        return START_NOT_STICKY
    }

    private val flags = when{
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.S ->
            PendingIntent.FLAG_MUTABLE
        else -> {PendingIntent.FLAG_MUTABLE}
    }

    private fun getNotification(input: String?, pendingIntent: PendingIntent?) {
        val notification = NotificationCompat.Builder(this, channelID)
            .setContentTitle(foregroundServiceNotificationTitle)
            .setContentText(input)
            .setSmallIcon(R.drawable.ic_clock_black_24dp)
            .setContentIntent(pendingIntent)
            .build()
        startForeground(1, notification)
    }

    override fun onBind(p0: Intent?): IBinder? {
       return null
    }

    override fun onDestroy() {
        super.onDestroy()
    }



}