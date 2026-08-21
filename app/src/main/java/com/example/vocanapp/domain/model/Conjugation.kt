package com.example.vocanapp.domain.model

data class Conjugation(
    val future: Map<String, String>,
    val present: Map<String, String>,
    val pastParticiple: String
)
