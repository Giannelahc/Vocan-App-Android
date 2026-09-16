package com.example.vocanapp.data.model.user


data class LanguageStatisticsResponse (
    val languageId: Int,
    var languageName: String,
    val totalWords: Int,
    val newWordsCurrentWeek: Int,
    val learnedWords: Int

)