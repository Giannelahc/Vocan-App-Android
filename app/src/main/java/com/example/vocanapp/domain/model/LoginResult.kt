package com.example.vocanapp.domain.model

data class LoginResult(
    val accessToken: String,
    val refreshToken: String,
    val tokenType: String
)