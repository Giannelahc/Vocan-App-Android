package com.example.vocanapp.data.model.user



data class PreferenceResponse (
    val id: Int,
    val nativeLanguage: LanguageResponse,
    val learningLanguages: List<LanguageResponse>
)