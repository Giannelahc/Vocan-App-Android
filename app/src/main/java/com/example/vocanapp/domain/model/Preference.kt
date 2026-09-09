package com.example.vocanapp.domain.model

data class Preference(
    val id: Int? = null,
    val nativeLanguage: Language,
    val learningLanguages: List<Language>
)
