package com.example.vocanapp.data.notification

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.Duration
import java.time.ZonedDateTime
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ReviewNotificationScheduler @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val workManager =
        WorkManager.getInstance(context)

    fun scheduleTest() {
        val request =
            OneTimeWorkRequestBuilder<ReviewNotificationWorker>()
                .setInitialDelay(3, TimeUnit.MINUTES)
                .build()

        workManager.enqueueUniqueWork(
            "review_notification_test",
            ExistingWorkPolicy.REPLACE,
            request
        )
    }

    fun schedule() {
        val initialDelay = calculateInitialDelay()

        val request =
            PeriodicWorkRequestBuilder<ReviewNotificationWorker>(
                24,
                TimeUnit.HOURS
            )
                .setInitialDelay(
                    initialDelay,
                    TimeUnit.MILLISECONDS
                )
                .build()

        workManager.enqueueUniquePeriodicWork(
            WORK_NAME,
            ExistingPeriodicWorkPolicy.UPDATE,
            request
        )
    }

    private fun calculateInitialDelay(): Long {

        val now = ZonedDateTime.now()

        var nextRun = now
            .withHour(NOTIFICATION_HOUR)
            .withMinute(0)
            .withSecond(0)
            .withNano(0)

        // Si ya pasó la hora de hoy,
        // programamos para mañana.
        if (!nextRun.isAfter(now)) {
            nextRun = nextRun.plusDays(1)
        }

        return Duration
            .between(now, nextRun)
            .toMillis()
    }

    companion object {
        private const val WORK_NAME =
            "review_notification_work"

        private const val NOTIFICATION_HOUR = 2
    }
}