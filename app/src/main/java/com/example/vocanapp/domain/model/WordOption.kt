package com.example.vocanapp.domain.model

data class WordOption(
    val text: String,
    val baseForm: String,
    val type: String,
    val alreadyRegistered: Boolean
)