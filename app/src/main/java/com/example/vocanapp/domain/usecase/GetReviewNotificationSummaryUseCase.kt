package com.example.vocanapp.domain.usecase

import com.example.vocanapp.domain.model.ReviewNotificationSummary
import com.example.vocanapp.domain.repository.ReviewRepository
import javax.inject.Inject

class GetReviewNotificationSummaryUseCase @Inject constructor(
    private val reviewRepository: ReviewRepository
) {

    suspend operator fun invoke(): ReviewNotificationSummary {
        return reviewRepository.getReviewNotificationSummary()
    }
}