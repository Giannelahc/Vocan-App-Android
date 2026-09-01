package com.example.vocanapp.presentation.main.review.session

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ReviewSessionRoute(
    reviewId: Int,
    onBackClick: () -> Unit,
    onReviewFinished: () -> Unit
) {

    val viewModel: ReviewSessionViewModel = hiltViewModel()

    val uiState = viewModel.uiState

    LaunchedEffect(reviewId) {

        viewModel.loadReview(reviewId)

    }

    LaunchedEffect(viewModel) {

        viewModel.events.collect { event ->

            when (event) {

                ReviewSessionEvent.ReviewFinished -> {
                    onReviewFinished()
                }
            }
        }
    }

    ReviewSessionScreen(
        uiState = uiState,

        onBackClick = onBackClick,

        onAnswerSelected = { answer ->
            viewModel.selectAnswer(answer)
        },

        onNextClick = {
            viewModel.nextExercise()
        },

        onFinishClick = {
            viewModel.finishReview()
        },
        onDoneClick = {
            viewModel.done()
        }
    )
}