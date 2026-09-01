package com.example.vocanapp.domain.usecase

import com.example.vocanapp.domain.model.CompleteReview
import com.example.vocanapp.domain.model.ReviewResult
import com.example.vocanapp.domain.repository.ReviewRepository
import javax.inject.Inject

class CompleteReviewUseCase @Inject constructor(
    private val reviewRepository: ReviewRepository
) {

    suspend operator fun invoke(
        reviewId: Int,
        answers: List<CompleteReview>
    ): ReviewResult {

        return reviewRepository.completeReview(
            reviewId = reviewId,
            answers = answers
        )
    }
}