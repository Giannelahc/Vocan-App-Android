package com.example.vocanapp.domain.model

import java.time.Instant

data class ReviewResult(
    val id: Int,
    val status: ReviewStatus,
    val nextReviewAt: Instant
)