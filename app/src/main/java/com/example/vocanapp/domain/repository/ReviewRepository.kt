package com.example.vocanapp.domain.repository

import com.example.vocanapp.domain.model.CompleteReview
import com.example.vocanapp.domain.model.ReviewDetail
import com.example.vocanapp.domain.model.ReviewHome
import com.example.vocanapp.domain.model.ReviewResult
import com.example.vocanapp.domain.model.ReviewSummaryPaginated

interface ReviewRepository {

    suspend fun getReviewsSummary(page: Int?, pageSize: Int?, status: String?): ReviewSummaryPaginated

    suspend fun getPendingReviewsSummary(page: Int?, pageSize: Int?): ReviewSummaryPaginated

    suspend fun getReviewDetail(reviewId: Int): ReviewDetail

    suspend fun getReviewHomeData(): ReviewHome

    suspend fun completeReview(reviewId: Int, answers: List<CompleteReview>): ReviewResult
}