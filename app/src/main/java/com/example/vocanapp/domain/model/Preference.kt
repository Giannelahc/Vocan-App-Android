package com.example.vocanapp.domain.model

data class Preference(
    val id: Int,
    val nativeLanguage: Language,
    val learningLanguages: List<Language>
)
