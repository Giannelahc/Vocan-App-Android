package com.example.vocanapp.data.model.vocabulary


data class VocabularyWordSummaryResponse(
    val id: Int,
    val word: String,
    val language: LanguageResponse?,
    val status: String,
    val senses: Int
)
