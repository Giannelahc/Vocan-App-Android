package com.example.vocanapp.domain.usecase

import com.example.vocanapp.domain.repository.ReviewRepository
import javax.inject.Inject

class ReviewFlashcardUseCase @Inject constructor(
    private val reviewRepository: ReviewRepository
) {

    suspend operator fun invoke(
        wordId: Int,
        rating: Int
    ): Unit {

        return reviewRepository.completeFlashcardReview(
            wordId = wordId,
            rating = rating
        )
    }
}