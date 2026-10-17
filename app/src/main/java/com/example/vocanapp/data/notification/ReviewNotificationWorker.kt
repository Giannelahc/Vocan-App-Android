package com.example.vocanapp.data.notification

import android.content.Context
import android.util.Log
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
        Log.d(
            "ReviewWorker",
            "START: time=${System.currentTimeMillis()}, " +
                    "id=$id, attempt=$runAttemptCount"
        )

        return try {
            val summary = getReviewNotificationSummaryUseCase()

            Log.d(
                "ReviewWorker",
                "SUMMARY: totalPending=${summary.totalPending}, " +
                        "overdueFlashcards=${summary.overdueFlashcards}, " +
                        "flashcardsToReview=${summary.flashcardsToReview}"
            )

            if (summary.totalPending > 0) {
                Log.d("ReviewWorker", "Showing review notification")

                ReviewNotificationHelper.show(
                    context = applicationContext,
                    summary = summary
                )
            } else {
                Log.d("ReviewWorker", "No pending reviews")
            }

            if (
                summary.overdueFlashcards +
                summary.flashcardsToReview > 0
            ) {
                Log.d("ReviewWorker", "Showing flashcard notification")

                FlashcardNotificationHelper.show(
                    context = applicationContext,
                    summary = summary
                )
            } else {
                Log.d("ReviewWorker", "No flashcards to review")
            }

            Log.d("ReviewWorker", "SUCCESS")
            Result.success()
        } catch (e: Exception) {
            Log.e("ReviewWorker", "Worker failed", e)
            Result.retry()
        }
    }
}