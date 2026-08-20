package com.example.vocanapp.domain.model

data class User (
    val id: Int? = null,
    val name: String,
    val lastname: String,
    val username: String,
    val email: String,
    val password: String
)