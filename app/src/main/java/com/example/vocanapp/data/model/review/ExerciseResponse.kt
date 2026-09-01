package com.example.vocanapp.data.model.review

data class ExerciseResponse (
    val id: Int,
    val type: String,
    val question: String,
    val options: List<String>,
    val correctAnswer: Int
)