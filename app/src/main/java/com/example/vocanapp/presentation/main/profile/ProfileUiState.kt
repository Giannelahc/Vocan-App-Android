package com.example.vocanapp.presentation.main.profile

import com.example.vocanapp.domain.model.Language

data class ProfileUiState(
    val username: String = "",
    val email: String = "",
    val preferenceId: Int? = null,
    val nativeLanguage: Language? = null,
    val learningLanguages: List<Language> = emptyList(),

    val originalNativeLanguage: Language? = null,
    val originalLearningLanguages: List<Language> = emptyList(),

    val availableLanguages: List<Language> = emptyList(),

    val isInitialSetup: Boolean = false,

    val isLoading: Boolean = false,
    val isSaving: Boolean = false,

    val error: String? = null
) {
    val hasChanges: Boolean
        get() =
            nativeLanguage?.id != originalNativeLanguage?.id ||
                    learningLanguages.map { it.id }.toSet() !=
                    originalLearningLanguages.map { it.id }.toSet()

    val canSave: Boolean
        get() =
            !isSaving &&
                    nativeLanguage != null &&
                    learningLanguages.isNotEmpty() &&
                    hasChanges
}