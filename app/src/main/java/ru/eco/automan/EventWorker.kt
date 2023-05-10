package ru.eco.automan

import android.content.Context
import android.content.SharedPreferences
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.eco.automan.viewModels.SettingsTypes
import ru.eco.automan.viewModels.getEstimatedDays

/**
 * Класс задачи для напоминания о предстоящих событиях
 */
class EventWorker(private val context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {

    override fun doWork(): Result {
        if (AutoApplication.sharedPreferences.getBoolean(SettingsTypes.NOTIFICATIONS.name, true)) {
            runBlocking {
                launch {
                    val events = AutoApplication.eventsRepository.getAllEvents()
                    events.forEach {
                        val days = it.date.getEstimatedDays()
                        if (days < 7) {
                            val daysToShow =
                                context.resources.getQuantityString(R.plurals.days, days, days)
                            AutoApplication.notificationManager.createEventNotification(
                                "Через $daysToShow событие: ${it.name}",
                                context
                            )
                        }
                    }
                }
            }
        }
        return Result.success()
    }
}