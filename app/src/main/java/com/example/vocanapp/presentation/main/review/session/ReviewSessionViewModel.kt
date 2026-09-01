package com.example.vocanapp.presentation.main.review.session

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vocanapp.domain.model.CompleteReview
import com.example.vocanapp.domain.usecase.CompleteReviewUseCase
import com.example.vocanapp.domain.usecase.GetReviewDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewSessionViewModel @Inject constructor(
    private val getReviewUseCase: GetReviewDetailUseCase,
    private val completeReviewUseCase: CompleteReviewUseCase
) : ViewModel() {

    var uiState by mutableStateOf(ReviewSessionUiState())
        private set

    private val _events = MutableSharedFlow<ReviewSessionEvent>()
    val events: SharedFlow<ReviewSessionEvent> =
        _events.asSharedFlow()

    fun loadReview(reviewId: Int) {

        if (uiState.review != null) {
            return
        }

        viewModelScope.launch {

            uiState = uiState.copy(
                isLoading = true,
                error = null
            )

            try {

                val review = getReviewUseCase(reviewId)

                uiState = uiState.copy(
                    isLoading = false,
                    review = review
                )

            } catch (e: Exception) {

                Log.e(
                    "REVIEW_SESSION",
                    "Error loading review",
                    e
                )

                uiState = uiState.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun selectAnswer(answerIndex: Int) {

        val exercise = uiState.currentExercise
            ?: return

        uiState = uiState.copy(
            selectedAnswers =
                uiState.selectedAnswers +
                        (exercise.id to answerIndex)
        )
    }

    fun nextExercise() {

        if (uiState.currentAnswer == null) {
            return
        }

        if (uiState.isLastExercise) {
            return
        }

        uiState = uiState.copy(
            currentExerciseIndex =
                uiState.currentExerciseIndex + 1
        )
    }

    fun done() {
        viewModelScope.launch {
            _events.emit(
                ReviewSessionEvent.ReviewFinished
            )
        }
    }

    fun finishReview() {

        val review = uiState.review
            ?: return

        if (uiState.isSubmitting) {
            return
        }

        // No permitimos terminar si falta responder
        // algún ejercicio.
        if (
            uiState.selectedAnswers.size
            != review.exercises.size
        ) {
            return
        }

        viewModelScope.launch {

            uiState = uiState.copy(
                isSubmitting = true,
                error = null
            )

            try {

                val answers = review.exercises.map { exercise ->

                    CompleteReview(
                        id = exercise.id,
                        answer = uiState.selectedAnswers[
                            exercise.id
                        ]!!
                    )
                }

                val result = completeReviewUseCase(
                    reviewId = review.id,
                    answers = answers
                )

                uiState = uiState.copy(
                    isSubmitting = false,
                    result = result
                )

                /*_events.emit(
                    ReviewSessionEvent.ReviewFinished
                )*/

            } catch (e: Exception) {

                Log.e(
                    "REVIEW_SESSION",
                    "Error completing review",
                    e
                )

                uiState = uiState.copy(
                    isSubmitting = false,
                    error = e.message
                )
            }
        }
    }
}