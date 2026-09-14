package com.example.vocanapp.presentation.main.vocabulary.detail.components.addUserExamples


data class AddUserExamplesUiState(
    val examples: List<String> = listOf(""),
    val isSaving: Boolean = false,
    val error: String? = null
)