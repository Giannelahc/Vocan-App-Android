package com.example.vocanapp.domain.model


data class LanguageStatistics (
    val languageId: Int,
    val languageName: String,
    val totalWords: Int,
    val newWordsCurrentWeek: Int,
    val learnedWords: Int

)