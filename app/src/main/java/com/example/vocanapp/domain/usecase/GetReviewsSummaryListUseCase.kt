package com.example.vocanapp.domain.usecase

import com.example.vocanapp.domain.model.ReviewSummaryPaginated
import com.example.vocanapp.domain.repository.ReviewRepository
import javax.inject.Inject

class GetReviewsSummaryListUseCase @Inject constructor(
    private val reviewRepository: ReviewRepository
) {

    suspend operator fun invoke(
        page: Int? = null,
        pageSize: Int? = null,
        status: String? = null
    ): ReviewSummaryPaginated {
        return reviewRepository.getReviewsSummary(page = page, pageSize = pageSize, status = status)
    }
}