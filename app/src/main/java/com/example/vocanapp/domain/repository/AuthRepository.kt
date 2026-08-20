package com.example.vocanapp.domain.repository

import com.example.vocanapp.domain.model.LoginResult
import com.example.vocanapp.domain.model.RegisterResult
import com.example.vocanapp.domain.model.User

interface AuthRepository {

    suspend fun login(
        email: String,
        password: String
    ): LoginResult

    suspend fun register(
        user: User
    ): RegisterResult
}