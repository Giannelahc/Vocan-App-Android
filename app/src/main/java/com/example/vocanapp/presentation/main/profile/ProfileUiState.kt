package com.example.vocanapp.presentation.main.profile

import com.example.vocanapp.domain.model.Language

data class ProfileUiState(
    val username: String = "",
    val email: String = "",
    val preferenceId: Int? = null,
    val nativeLanguage: Language? = null,
    val learningLanguages: List<Language> = emptyList(),

    val availableLanguages: List<Language> = emptyList(),

    val isLoading: Boolean = false,
    val isSaving: Boolean = false,

    val error: String? = null
)