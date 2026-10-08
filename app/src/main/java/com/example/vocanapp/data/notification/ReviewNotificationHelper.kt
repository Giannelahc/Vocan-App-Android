package com.example.vocanapp.data.notification

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.vocanapp.MainActivity
import com.example.vocanapp.R
import com.example.vocanapp.domain.model.ReviewNotificationSummary

object ReviewNotificationHelper {

    private const val CHANNEL_ID = "review_reminders"
    private const val CHANNEL_NAME = "Review reminders"

    private const val NOTIFICATION_ID = 1001

    fun show(
        context: Context,
        summary: ReviewNotificationSummary
    ) {

        createNotificationChannel(context)

        val intent = Intent(
            context,
            MainActivity::class.java
        ).apply {
            flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TOP

            putExtra(
                "destination",
                "review"
            )
        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or
                    PendingIntent.FLAG_IMMUTABLE
        )

        val message = buildMessage(summary)

        val notification =
            NotificationCompat.Builder(
                context,
                CHANNEL_ID
            )
                .setSmallIcon(
                    R.drawable.happy_michita
                )
                .setContentTitle(
                    "Review Reminder"
                )
                .setContentText(
                    message
                )
                .setStyle(
                    NotificationCompat.BigTextStyle()
                        .bigText(message)
                )
                .setPriority(
                    NotificationCompat.PRIORITY_DEFAULT
                )
                .setAutoCancel(true)
                .setContentIntent(
                    pendingIntent
                )
                .build()

        if (
            Build.VERSION.SDK_INT < 33 ||
            ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {

            NotificationManagerCompat
                .from(context)
                .notify(
                    NOTIFICATION_ID,
                    notification
                )
        }
    }

    private fun buildMessage(
        summary: ReviewNotificationSummary
    ): String {

        return when {

            summary.overdueReviews > 0 -> {

                "${summary.userName}, you have ${summary.totalPending} " +
                        "reviews waiting. And " +
                        "${summary.overdueReviews} " +
                        "are overdue."
            }

            else -> {

                "${summary.userName}, you have ${summary.todayReviews} " +
                        "reviews to complete today."
            }
        }
    }

    private fun createNotificationChannel(
        context: Context
    ) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )

            val manager =
                context.getSystemService(
                    NotificationManager::class.java
                )

            manager.createNotificationChannel(channel)
        }
    }
}