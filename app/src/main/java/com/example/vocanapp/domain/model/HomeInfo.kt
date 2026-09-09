package com.example.vocanapp.domain.model


data class HomeInfo (
    val id: Int,
    val name: String,
    val lastname: String,
    val username: String,
    val newWordsCurrentWeek: Int,
    val learnedWords: Int

)