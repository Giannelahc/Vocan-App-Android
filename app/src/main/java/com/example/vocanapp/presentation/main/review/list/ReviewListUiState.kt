package com.example.vocanapp.presentation.main.review.list

import com.example.vocanapp.domain.model.ReviewSummary

data class ReviewListUiState(
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val error: String? = null,

    val reviews: List<ReviewSummary> = emptyList(),

    val totalReviews: Int = 0,
    val currentPage: Int = 0,
    val pageSize: Int = 20,
    val hasMore: Boolean = true
)