package com.example.vocanapp.presentation.main.flashcard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vocanapp.domain.model.ReviewRating
import com.example.vocanapp.domain.mapper.toFlashcard
import com.example.vocanapp.domain.usecase.GetWordDetailUseCase
import com.example.vocanapp.domain.usecase.GetWordsListUseCase
import com.example.vocanapp.domain.usecase.ReviewFlashcardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlashcardsViewModel @Inject constructor(
    private val getDueFlashcardsUseCase: GetWordsListUseCase,
    private val getVocabularyDetailUseCase: GetWordDetailUseCase,
    private val reviewFlashcardUseCase: ReviewFlashcardUseCase
) : ViewModel() {

    var uiState by mutableStateOf(
        FlashcardsUiState()
    )
        private set

    private val _events = MutableSharedFlow<FlashcardsEvent>()
    val events = _events.asSharedFlow()

    fun loadFlashcards() {
        viewModelScope.launch {

            uiState = uiState.copy(
                isLoading = true,
                error = null
            )

            try {
                val summaries =
                    getDueFlashcardsUseCase(
                        reviewLevel = 3,
                        due = true
                    )

                uiState = uiState.copy(
                    flashcardSummaries = summaries.items
                )

                loadNextDetails()

            } catch (e: Exception) {
                uiState = uiState.copy(
                    error = e.message
                )
            } finally {
                uiState = uiState.copy(
                    isLoading = false
                )
            }
        }
    }

    fun showAnswer() {
        uiState = uiState.copy(
            revealed = true
        )
    }

    fun reviewFlashcard(
        page: Int,
        rating: ReviewRating
    ) {
        viewModelScope.launch {

            val currentFlashcard =
                uiState.flashcards.getOrNull(page)
                    ?: return@launch

            uiState = uiState.copy(
                isReviewing = true
            )

            try {

                reviewFlashcardUseCase(
                    wordId = currentFlashcard.id,
                    rating = rating.value
                )

                if (rating == ReviewRating.AGAIN) {
                    uiState = uiState.copy(
                        againFlashcards =
                            uiState.againFlashcards + currentFlashcard
                    )
                }

                val isLastFlashcard =
                    page == uiState.flashcards.lastIndex

                if (isLastFlashcard) {

                    val cardsToRepeat =
                        if (rating == ReviewRating.AGAIN) {
                            uiState.againFlashcards + currentFlashcard
                        } else {
                            uiState.againFlashcards
                        }

                    if (cardsToRepeat.isNotEmpty()) {

                        uiState = uiState.copy(
                            flashcards = cardsToRepeat,
                            againFlashcards = emptyList(),
                            revealed = false,
                            isReviewing = false
                        )

                        _events.emit(
                            FlashcardsEvent.Restarted
                        )

                    } else {

                        uiState = uiState.copy(
                            flashcards = emptyList(),
                            revealed = false,
                            isReviewing = false
                        )

                        _events.emit(
                            FlashcardsEvent.Completed
                        )
                    }

                } else {

                    uiState = uiState.copy(
                        revealed = false,
                        isReviewing = false
                    )

                    _events.emit(
                        FlashcardsEvent.ReviewCompleted
                    )

                    loadNextDetails()
                }

            } catch (e: Exception) {

                uiState = uiState.copy(
                    isReviewing = false,
                    error = e.message
                )
            }
        }
    }

    private suspend fun loadNextDetails() {

        if (uiState.isLoadingNextDetails) {
            return
        }

        uiState = uiState.copy(
            isLoadingNextDetails = true
        )

        try {
            val summaries = uiState.flashcardSummaries

            val loadedIds = uiState.flashcards
                .map { it.id }
                .toSet()

            val nextSummaries = summaries
                .filter { it.id !in loadedIds }
                .take(2)

            val newFlashcards = coroutineScope {
                nextSummaries
                    .map { summary ->
                        async {
                            getVocabularyDetailUseCase(summary.id)
                                .toFlashcard()
                        }
                    }
                    .awaitAll()
            }

            uiState = uiState.copy(
                flashcards =
                    uiState.flashcards + newFlashcards
            )

        } finally {
            uiState = uiState.copy(
                isLoadingNextDetails = false
            )
        }
    }

}