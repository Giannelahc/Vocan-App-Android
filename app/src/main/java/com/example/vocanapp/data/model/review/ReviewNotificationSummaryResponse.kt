package com.example.vocanapp.data.model.review


data class ReviewNotificationSummaryResponse(
    val totalPending: Int,
    val todayReviews: Int,
    val overdueReviews: Int,
    val userName: String,
    val flashcardsToReview: Int,
    val overdueFlashcards: Int
)