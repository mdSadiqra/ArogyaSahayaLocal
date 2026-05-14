package com.sadiq.arogyasahayalocal.notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.sadiq.arogyasahayalocal.R
import com.sadiq.arogyasahayalocal.ui.screens.AlarmActivity

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(
        context: Context,
        intent: Intent
    ) {

        val medicineName =
            intent.getStringExtra("medicine_name")
                ?: "Medicine Reminder"

        /*
        --------------------------------
        FULL SCREEN ACTIVITY INTENT
        --------------------------------
        */

        val fullScreenIntent = Intent(
            context,
            AlarmActivity::class.java
        ).apply {
            putExtra(
                "medicine_name",
                medicineName
            )

            flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TOP or
                        Intent.FLAG_ACTIVITY_SINGLE_TOP
        }

        val fullScreenPendingIntent =
            PendingIntent.getActivity(
                context,
                100,
                fullScreenIntent,
                PendingIntent.FLAG_UPDATE_CURRENT or
                        PendingIntent.FLAG_IMMUTABLE
            )

        /*
        --------------------------------
        STRONG ALARM NOTIFICATION
        --------------------------------
        */

        val notification =
            NotificationCompat.Builder(
                context,
                NotificationHelper.CHANNEL_ID
            )
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Medicine Reminder")
                .setContentText(
                    "Time to take: $medicineName"
                )
                .setPriority(
                    NotificationCompat.PRIORITY_MAX
                )
                .setCategory(
                    NotificationCompat.CATEGORY_ALARM
                )
                .setAutoCancel(true)
                .setOngoing(true)
                .setFullScreenIntent(
                    fullScreenPendingIntent,
                    true
                )
                .build()

        val manager =
            context.getSystemService(
                Context.NOTIFICATION_SERVICE
            ) as NotificationManager

        manager.notify(
            System.currentTimeMillis().toInt(),
            notification
        )
    }
}