package com.example.vocanapp.data.repository

import com.example.vocanapp.data.mapper.toDomain
import com.example.vocanapp.data.mapper.toRequest
import com.example.vocanapp.data.remote.ReviewApi
import com.example.vocanapp.domain.model.CompleteReview
import com.example.vocanapp.domain.model.ReviewDetail
import com.example.vocanapp.domain.model.ReviewHome
import com.example.vocanapp.domain.model.ReviewNotificationSummary
import com.example.vocanapp.domain.model.ReviewResult
import com.example.vocanapp.domain.model.ReviewSummaryPaginated
import com.example.vocanapp.domain.repository.ReviewRepository
import javax.inject.Inject

class ReviewRepositoryImpl @Inject constructor(
    private val api: ReviewApi
) : ReviewRepository {

    override suspend fun getReviewsSummary(page: Int?, pageSize: Int?, status: String?): ReviewSummaryPaginated {
        return api.getReviewsSummary(page = page, pageSize = pageSize, status = status).toDomain()
    }

    override suspend fun getPendingReviewsSummary(
        page: Int?,
        pageSize: Int?
    ): ReviewSummaryPaginated {
        return api.getPendingReviewsSummary(page = page, pageSize = pageSize).toDomain()
    }

    override suspend fun getReviewDetail(reviewId: Int): ReviewDetail {
        return api.getReviewDetail(reviewId).toDomain()
    }

    override suspend fun getReviewHomeData(): ReviewHome {
        return api.getReviewHomeData().toDomain()
    }

    override suspend fun completeReview(reviewId: Int, answers: List<CompleteReview>): ReviewResult {
        return api.completeReview(reviewId, answers.map { it.toRequest() }).toDomain()
    }

    override suspend fun getReviewNotificationSummary(): ReviewNotificationSummary {
        return api.getReviewNotificationSummary().toDomain()
    }

}