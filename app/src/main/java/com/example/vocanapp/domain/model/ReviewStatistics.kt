package com.example.vocanapp.domain.model


data class ReviewStatistics(
    val successRate: Double = 0.0,
    val reviewsToReview: Int = 0,
    val overdueReviews: Int = 0
)