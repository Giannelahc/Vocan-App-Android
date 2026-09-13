package com.example.vocanapp.presentation.main.vocabulary

import com.example.vocanapp.domain.model.Language
import com.example.vocanapp.domain.model.WordSummary

data class VocabularyUiState(
    val words: List<WordSummary> = emptyList(),
    val selectedLanguage: Language? = null,
    val languages: List<Language> = emptyList(),
    val searchQuery: String = "",
    val totalWords: Int = 0,
    val currentPage: Int = 0,
    val pageSize: Int = 50,
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val refreshTrigger: Int = 0,
    val hasMore: Boolean = true,
    val error: String? = null
)