package com.example.vocanapp.domain.model

data class Flashcard(
    val id: Int,
    val word: String,
    val translations: List<FlashcardTranslation>,
    val synonyms: List<String>,
    val example: List<String>
)

data class FlashcardTranslation(
    val languageCode: String,
    val words: List<String>
)