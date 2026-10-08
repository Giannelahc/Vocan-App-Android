package com.example.vocanapp.presentation.main.flashcard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vocanapp.domain.model.ReviewRating
import com.example.vocanapp.presentation.main.LettersLoading
import com.example.vocanapp.presentation.main.flashcard.components.EmptyFlashcardsState
import com.example.vocanapp.presentation.main.flashcard.components.Flashcard
import com.example.vocanapp.presentation.main.flashcard.components.RatingButtons
import com.example.vocanapp.presentation.main.flashcard.components.ShowAnswerButton

@Composable
fun FlashcardsScreen(
    uiState: FlashcardsUiState,
    pagerState: PagerState,
    onShowAnswer: () -> Unit,
    onRatingSelected: (ReviewRating) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        when {
            uiState.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    LettersLoading(
                        modifier = Modifier.size(40.dp)
                    )
                }
            }

            uiState.flashcards.isEmpty() -> {
                EmptyFlashcardsState()
            }

            else -> {

                HorizontalPager(
                    state = pagerState,
                    userScrollEnabled = false,
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(
                        horizontal = 12.dp
                    ),
                    pageSpacing = 12.dp
                ) { page ->

                    val flashcard = uiState.flashcards[page]

                    Flashcard(
                        flashcard = flashcard,
                        revealed = uiState.revealed
                    )
                }

                if (uiState.revealed) {
                    RatingButtons(
                        onRatingSelected = onRatingSelected
                    )
                } else {
                    ShowAnswerButton(
                        onClick = onShowAnswer
                    )
                }
            }
        }
    }
}