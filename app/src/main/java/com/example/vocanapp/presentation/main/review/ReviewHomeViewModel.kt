package com.example.vocanapp.presentation.main.review

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vocanapp.domain.model.ReviewStatistics
import com.example.vocanapp.domain.usecase.GetReviewHomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewHomeViewModel @Inject constructor(
    private val getReviewHomeUseCase: GetReviewHomeUseCase
) : ViewModel() {

    var uiState by mutableStateOf(ReviewHomeUiState())
        private set


    fun loadHome() {

        if (uiState.isLoading) {
            return
        }

        viewModelScope.launch {

            uiState = uiState.copy(
                isLoading = true,
                error = null
            )

            try {

                val response = getReviewHomeUseCase()

                uiState = uiState.copy(
                    isLoading = false,
                    statistics = ReviewStatistics(
                        successRate = response.statistics.successRate,
                        reviewsToReview = response.statistics.reviewsToReview,
                        overdueReviews = response.statistics.overdueReviews
                    ),
                    pendingReviews = response.pendingReviews,
                    error = null
                )

            } catch (e: Exception) {

                Log.e(
                    "REVIEW_HOME",
                    "Error loading review home",
                    e
                )

                uiState = uiState.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun refresh() {
        loadHome()
    }
}