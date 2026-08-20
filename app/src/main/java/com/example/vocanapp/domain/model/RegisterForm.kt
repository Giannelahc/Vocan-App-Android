package com.example.vocanapp.domain.model

data class RegisterForm(
    val name: String = "",
    val lastname: String = "",
    val username: String = "",
    val email: String = "",
    val password: String = ""
)