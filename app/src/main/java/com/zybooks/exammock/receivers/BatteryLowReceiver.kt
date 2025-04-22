package com.zybooks.exammock.receivers

import android.app.Notification
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.zybooks.exammock.R


class BatteryLowReceiver:BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        Toast.makeText(context,"Battery is low- pause background",Toast.LENGTH_LONG).show()
        val notification = NotificationCompat.Builder(context,"battery_channel")
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("Battery Low")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        //NotificationManagerCompat.from(context).notify(101,notification)

    }
}