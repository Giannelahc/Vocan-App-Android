package com.example.vocanapp.data.model.review


data class ReviewStatisticsResponse(
    val successRate: Double,
    val reviewsToReview: Int,
    val overdueReviews: Int
)