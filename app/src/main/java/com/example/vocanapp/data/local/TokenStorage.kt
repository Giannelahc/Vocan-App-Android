package com.example.vocanapp.data.local

interface TokenStorage {

    suspend fun saveToken(token: String)

    suspend fun getToken(): String?

    suspend fun clearToken()
}