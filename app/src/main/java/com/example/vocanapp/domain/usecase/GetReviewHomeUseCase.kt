package com.example.vocanapp.domain.usecase

import com.example.vocanapp.domain.model.ReviewHome
import com.example.vocanapp.domain.repository.ReviewRepository
import javax.inject.Inject

class GetReviewHomeUseCase @Inject constructor(
    private val reviewRepository: ReviewRepository
) {

    suspend operator fun invoke(): ReviewHome {
        return reviewRepository.getReviewHomeData()
    }
}