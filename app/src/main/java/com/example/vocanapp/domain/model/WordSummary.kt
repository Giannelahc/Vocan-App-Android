package com.example.vocanapp.domain.model


data class WordSummary(
    val id: Int,
    val word: String,
    val language: Language?,
    val status: String,
    val senses: Int
)
