package com.example.vocanapp.data.session

import com.example.vocanapp.data.local.TokenStorage
import com.example.vocanapp.domain.usecase.GetCurrentUserUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionInitializer @Inject constructor(
    private val tokenStorage: TokenStorage,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val sessionManager: SessionManager
) {

    suspend fun initialize() {

        val accessToken = tokenStorage.getToken()
        val refreshToken = tokenStorage.getRefreshToken()

        if (
            accessToken.isNullOrBlank() ||
            refreshToken.isNullOrBlank()
        ) {
            sessionManager.setLoggedOut()
            return
        }

        try {
            getCurrentUserUseCase()
            sessionManager.setLoggedIn()
        } catch (e: Exception) {
            sessionManager.setLoggedOut()
        }
    }
}