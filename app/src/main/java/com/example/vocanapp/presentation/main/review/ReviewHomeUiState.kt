package com.example.vocanapp.presentation.main.review

import com.example.vocanapp.domain.model.ReviewStatistics
import com.example.vocanapp.domain.model.ReviewSummary

data class ReviewHomeUiState(
    val isLoading: Boolean = false,
    val error: String? = null,

    val statistics: ReviewStatistics = ReviewStatistics(),

    val pendingReviews: List<ReviewSummary> = emptyList()
)