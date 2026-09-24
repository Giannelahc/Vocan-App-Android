package com.example.vocanapp.presentation.main.flashcard

import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun FlashcardRoute() {
    val viewModel: FlashcardsViewModel = hiltViewModel()
    val uiState = viewModel.uiState

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = {
            uiState.flashcards.size
        }
    )

    LaunchedEffect(Unit) {
        viewModel.loadFlashcards()
    }

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {

                FlashcardsEvent.ReviewCompleted -> {
                    val nextPage = pagerState.currentPage + 1

                    pagerState.animateScrollToPage(
                        nextPage
                    )
                }

                FlashcardsEvent.Restarted -> {
                    pagerState.scrollToPage(0)
                }

                FlashcardsEvent.Completed -> {
                }
            }
        }
    }

    FlashcardsScreen(
        uiState = uiState,
        pagerState = pagerState,
        onShowAnswer = {
            viewModel.showAnswer()
        },
        onRatingSelected = { rating ->
            viewModel.reviewFlashcard(
                page = pagerState.currentPage,
                rating = rating
            )
        }
    )
}