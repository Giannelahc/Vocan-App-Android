package com.example.vocanapp.data.model.register


data class RegisterUserRequest(
    val name: String,
    val lastname: String,
    val username: String,
    val email: String,
    val password: String
)