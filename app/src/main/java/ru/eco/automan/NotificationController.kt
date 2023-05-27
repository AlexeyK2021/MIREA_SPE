package ru.eco.automan

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.provider.Settings.Global.getString
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService

/**
 * Контроллер уведомлений
 */
class NotificationController {
    companion object {
        var NOTIFICATION_ID = 101
        const val CHANNEL_ID = "channelID"
    }

//    @SuppressLint("MissingPermission")
//    fun createTestNotification(context: Context) {
//        val intent = Intent(context, MainActivity::class.java)
//        intent.apply {
//            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        }
//        val pendingIntent =
//            PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
//
//        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
//            .setSmallIcon(R.drawable.baseline_directions_car_24)
//            .setContentTitle("Напоминание")
//            .setContentText("Тестим уведомление")
//            .setAutoCancel(true)
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//            .setContentIntent(pendingIntent)
//
//        val notificationManager = NotificationManagerCompat.from(context)
//        notificationManager.notify(NOTIFICATION_ID++, builder.build())
//
//    }

    /**
     * Метод создания и посыла уведомления пользователю
     * @param text текст уведомления
     */
    @SuppressLint("MissingPermission")
    fun createEventNotification(text: String, context: Context) {
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_directions_car_24)
            .setContentTitle("Напоминание")
            .setContentText(text)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(NOTIFICATION_ID++, builder.build())
    }

}