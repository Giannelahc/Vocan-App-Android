package com.example.vocanapp.domain.repository

import com.example.vocanapp.domain.model.UserInfo

interface UserRepository {

    suspend fun getCurrentUser(): UserInfo

}