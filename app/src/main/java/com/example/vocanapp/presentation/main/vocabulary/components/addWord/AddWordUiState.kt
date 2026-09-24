package com.example.vocanapp.presentation.main.vocabulary.components.addWord

import com.example.vocanapp.domain.model.Language

data class AddWordUiState(
    val word: String = "",
    val selectedLanguage: Language? = null,
    val isLoading: Boolean = false,
    val isAdded: Boolean = false,
    val error: String? = null,
    val isInvalidWord: Boolean = false
)