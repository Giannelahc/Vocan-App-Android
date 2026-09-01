package com.example.vocanapp.domain.usecase

import com.example.vocanapp.domain.model.ReviewSummaryPaginated
import com.example.vocanapp.domain.repository.ReviewRepository
import javax.inject.Inject

class GetPendingReviewsSummaryListUseCase @Inject constructor(
    private val reviewRepository: ReviewRepository
) {

    suspend operator fun invoke(
        page: Int? = null,
        pageSize: Int? = null
    ): ReviewSummaryPaginated {
        return reviewRepository.getPendingReviewsSummary(page = page, pageSize = pageSize)
    }
}