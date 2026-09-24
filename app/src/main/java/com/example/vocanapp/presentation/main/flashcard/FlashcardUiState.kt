package com.example.vocanapp.presentation.main.flashcard

import com.example.vocanapp.domain.model.Flashcard
import com.example.vocanapp.domain.model.WordSummary

data class FlashcardsUiState(
    val flashcardSummaries: List<WordSummary> = emptyList(),
    val flashcards: List<Flashcard> = emptyList(),

    val againFlashcards: List<Flashcard> = emptyList(),

    val currentPage: Int = 0,

    val revealed: Boolean = false,

    val isLoading: Boolean = false,
    val isLoadingNextDetails: Boolean = false,
    val isReviewing: Boolean = false,

    val error: String? = null
)