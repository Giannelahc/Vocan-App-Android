package com.example.vocanapp.domain.model

import java.time.Instant

data class ReviewSummary(
    val id: Int,
    val word: String,
    val exerciseCount: Int,
    val nextReviewAt: Instant,
    val completedAt: Instant?,
    val reviewLevel: Int
)

fun ReviewSummary.isOverdue(): Boolean {
    return nextReviewAt.isBefore(Instant.now())
}