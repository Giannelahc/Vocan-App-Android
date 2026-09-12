package com.example.vocanapp.data.notification

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.vocanapp.domain.usecase.GetReviewNotificationSummaryUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class ReviewNotificationWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val getReviewNotificationSummaryUseCase:
    GetReviewNotificationSummaryUseCase
) : CoroutineWorker(
    appContext,
    workerParams
) {

    override suspend fun doWork(): Result {

        return try {

            val summary =
                getReviewNotificationSummaryUseCase()

            if (summary.totalPending > 0) {

                ReviewNotificationHelper.show(
                    context = applicationContext,
                    summary = summary
                )
            }

            Result.success()

        } catch (e: Exception) {

            Result.retry()
        }
    }
}