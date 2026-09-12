package com.example.vocanapp.data.model.login

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String,
    val tokenType: String
)