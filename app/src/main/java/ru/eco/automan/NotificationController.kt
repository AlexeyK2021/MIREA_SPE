package ru.eco.automan

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

/**
 * Контроллер уведомлений
 */
class NotificationController() {
    companion object {
        var NOTIFICATION_ID = 101
        const val CHANNEL_ID = "channelID"
    }

    @SuppressLint("MissingPermission")
    fun createTestNotification(context: Context) {
        val intent = Intent(context, MainActivity::class.java)
        intent.apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

        // Создаём уведомление
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_directions_car_24)
            .setContentTitle("Напоминание")
            .setContentText("Тестим уведомление")
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)

        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(NOTIFICATION_ID++, builder.build())

    }
}