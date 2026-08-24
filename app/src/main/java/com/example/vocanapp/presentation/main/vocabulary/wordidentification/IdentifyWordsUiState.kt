package com.example.vocanapp.presentation.main.vocabulary.wordidentification

import com.example.vocanapp.domain.model.Language
import com.example.vocanapp.domain.model.WordOption

data class IdentifyWordsUiState(
    val sentence: String = "",
    val isLoading: Boolean = false,
    val options: List<WordOption> = emptyList(),
    val selectedWordIds: Set<String> = emptySet(),
    val language: Language? = Language(1, "fr", "French"),
    val isAdding: Boolean = false,
    val error: String? = null,
    val languages: List<Language> = emptyList()
)