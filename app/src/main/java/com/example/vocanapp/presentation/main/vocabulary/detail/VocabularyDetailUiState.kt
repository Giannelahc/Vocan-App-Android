package com.example.vocanapp.presentation.main.vocabulary.detail

import com.example.vocanapp.domain.model.Word

data class VocabularyDetailUiState(
    val word: Word? = null,
    val isLoading: Boolean = false,
    val hasMore: Boolean = true,
    val error: String? = null
)