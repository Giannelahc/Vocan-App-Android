package com.example.vocanapp.data.model.review


data class ReviewHomeResponse(
    val statistics: ReviewStatisticsResponse,
    val pendingReviews: List<ReviewSummaryResponse>
)