package com.example.vocanapp.data.model.user


data class HomeInfoResponse (
    val id: Int,
    val name: String,
    val lastname: String,
    val username: String,
    val newWordsCurrentWeek: Int,
    val learnedWords: Int

)