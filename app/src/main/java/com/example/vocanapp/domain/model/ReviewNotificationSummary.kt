package com.example.vocanapp.domain.model


data class ReviewNotificationSummary(
    val totalPending: Int,
    val todayReviews: Int,
    val overdueReviews: Int,
    val userName: String,
    val flashcardsToReview: Int,
    val overdueFlashcards: Int
)