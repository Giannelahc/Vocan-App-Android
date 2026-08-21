package com.example.vocanapp.domain.model


data class Word(
    val id: Int,
    val word: String,
    val language: Language,
    val status: String,
    val senses: List<WordSense>
)
