package com.example.vocanapp.domain.usecase

import com.example.vocanapp.domain.model.ReviewDetail
import com.example.vocanapp.domain.repository.ReviewRepository
import javax.inject.Inject

class GetReviewDetailUseCase @Inject constructor(
    private val reviewRepository: ReviewRepository
) {

    suspend operator fun invoke(
        reviewId: Int
    ): ReviewDetail {
        return reviewRepository.getReviewDetail(
            reviewId =  reviewId
        )
    }
}