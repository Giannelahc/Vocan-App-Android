package com.example.vocanapp.data.model.review


data class ReviewSummaryPaginatedResponse(
    val page: Int,
    val pageSize: Int,
    val total: Int,
    val items: List<ReviewSummaryResponse>
)