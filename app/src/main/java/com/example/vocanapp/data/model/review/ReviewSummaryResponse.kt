package com.example.vocanapp.data.model.review


data class ReviewSummaryResponse(
    val id: Int,
    val word: String,
    val exercises: Int,
    val nextReviewAt: String?,
    val completedAt: String?,
    val reviewLevel: Int
)