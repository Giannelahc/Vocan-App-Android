package com.example.vocanapp.presentation.main.review.list

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vocanapp.domain.usecase.GetPendingReviewsSummaryListUseCase
import com.example.vocanapp.domain.usecase.GetReviewsSummaryListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewListViewModel @Inject constructor(
    private val getReviewsUseCase: GetPendingReviewsSummaryListUseCase
) : ViewModel() {

    var uiState by mutableStateOf(ReviewListUiState())
        private set

    private var loadJob: Job? = null

    init {
        loadInitialReviews()
    }

    fun refresh() {

        loadJob?.cancel()

        loadJob = viewModelScope.launch {
            loadReviews(
                page = 1,
                isInitialLoad = true
            )
        }
    }

    private fun loadInitialReviews() {

        loadJob?.cancel()

        loadJob = viewModelScope.launch {
            loadReviews(
                page = 1,
                isInitialLoad = true
            )
        }
    }

    fun loadMore() {

        if (
            uiState.isLoading ||
            uiState.isLoadingMore ||
            !uiState.hasMore
        ) {
            return
        }

        loadJob?.cancel()

        loadJob = viewModelScope.launch {
            loadReviews(
                page = uiState.currentPage + 1,
                isInitialLoad = false
            )
        }
    }

    private suspend fun loadReviews(
        page: Int,
        isInitialLoad: Boolean
    ) {

        uiState = uiState.copy(
            isLoading = isInitialLoad,
            isLoadingMore = !isInitialLoad,
            error = null
        )

        try {

            val response = getReviewsUseCase(
                page = page,
                pageSize = uiState.pageSize
            )

            val reviews = if (isInitialLoad) {
                response.items
            } else {
                uiState.reviews + response.items
            }

            uiState = uiState.copy(
                isLoading = false,
                isLoadingMore = false,
                reviews = reviews,
                totalReviews = response.total,
                currentPage = response.page,
                hasMore =
                    response.page * response.pageSize < response.total
            )

        } catch (e: Exception) {

            Log.e(
                "REVIEW_LIST",
                "Error loading reviews",
                e
            )

            uiState = uiState.copy(
                isLoading = false,
                isLoadingMore = false,
                error = e.message
            )
        }
    }
}