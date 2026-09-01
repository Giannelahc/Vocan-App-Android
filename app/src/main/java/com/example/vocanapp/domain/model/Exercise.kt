package com.example.vocanapp.domain.model

data class Exercise (
    val id: Int,
    val type: String,
    val question: String,
    val options: List<String>,
    val correctAnswer: Int
)