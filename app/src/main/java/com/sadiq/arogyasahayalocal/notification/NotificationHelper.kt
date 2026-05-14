package com.sadiq.arogyasahayalocal.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.Build

object NotificationHelper {

    const val CHANNEL_ID = "medicine_alarm_channel"
    const val CHANNEL_NAME = "Medicine Alarm Reminder"

    fun createNotificationChannel(
        context: Context
    ) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val alarmSound = RingtoneManager.getDefaultUri(
                RingtoneManager.TYPE_ALARM
            )

            val audioAttributes =
                AudioAttributes.Builder()
                    .setUsage(
                        AudioAttributes.USAGE_ALARM
                    )
                    .setContentType(
                        AudioAttributes.CONTENT_TYPE_SONIFICATION
                    )
                    .build()

            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {

                description =
                    "Full screen medicine reminder alarms"

                enableVibration(true)

                vibrationPattern = longArrayOf(
                    0,
                    1000,
                    500,
                    1000,
                    500,
                    1000
                )

                setSound(
                    alarmSound,
                    audioAttributes
                )

                lockscreenVisibility =
                    Notification.VISIBILITY_PUBLIC
            }

            val manager =
                context.getSystemService(
                    Context.NOTIFICATION_SERVICE
                ) as NotificationManager

            manager.createNotificationChannel(
                channel
            )
        }
    }
}