package com.example.vocanapp.data.model.vocabulary

data class WordOptionResponse(
    val text: String,
    val type: String,
    val baseForm: String,
    val alreadyRegistered: Boolean
)