package com.example.vocanapp.data.model.preference

import com.example.vocanapp.data.model.user.LanguageResponse

data class PreferenceResponse (
    val id: Int,
    val nativeLanguage: LanguageResponse,
    val learningLanguages: List<LanguageResponse>
)