package com.example.vocanapp.domain.model


data class ReviewHome(
    val statistics: ReviewStatistics,
    val pendingReviews: List<ReviewSummary>
)