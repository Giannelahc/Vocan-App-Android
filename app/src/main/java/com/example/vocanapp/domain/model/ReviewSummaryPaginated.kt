package com.example.vocanapp.domain.model


data class ReviewSummaryPaginated(
    val items: List<ReviewSummary>,
    val page: Int,
    val pageSize: Int,
    val total: Int
)