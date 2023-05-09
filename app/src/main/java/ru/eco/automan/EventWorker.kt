package ru.eco.automan

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.eco.automan.viewModels.getEstimatedDays

class EventWorker(private val context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {

    override fun doWork(): Result {
//        AutoApplication.notificationManager.createEventNotification(
//            context = context,
//            text = Random(Calendar.getInstance().timeInMillis).nextInt().toString()
//        )

        runBlocking {
            launch {
                val events = AutoApplication.eventsRepository.getAllEvents()
                events.forEach {
                    val days = it.date.getEstimatedDays()
                    if (days < 7) {
                        val daysToShow = context.resources.getQuantityString(R.plurals.days, days, days)
                        AutoApplication.notificationManager.createEventNotification(
                            "Через $daysToShow событие: ${it.name}",
                            context
                        )
                    }
                }
            }
        }
        return Result.success()
    }
}