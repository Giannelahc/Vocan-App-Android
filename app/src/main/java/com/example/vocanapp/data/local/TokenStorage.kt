package com.example.vocanapp.data.local

interface TokenStorage {

    suspend fun saveToken(token: String, refreshToken: String)

    suspend fun getToken(): String?

    suspend fun getRefreshToken(): String?

    suspend fun clearToken()
}