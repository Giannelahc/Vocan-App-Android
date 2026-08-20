package com.example.vocanapp.data.session

import com.example.vocanapp.data.local.TokenStorage
import com.example.vocanapp.presentation.session.SessionState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor(
    private val tokenStorage: TokenStorage
) {

    private val _sessionState =
        MutableStateFlow<SessionState>(SessionState.Loading)

    val sessionState: StateFlow<SessionState> =
        _sessionState.asStateFlow()

    suspend fun initialize() {

        val token = tokenStorage.getToken()

        _sessionState.value =
            if (token.isNullOrBlank()) {
                SessionState.LoggedOut
            } else {
                SessionState.LoggedIn
            }
    }

    suspend fun login(token: String) {

        tokenStorage.saveToken(token)

        _sessionState.value =
            SessionState.LoggedIn
    }

    suspend fun logout() {

        tokenStorage.clearToken()

        _sessionState.value =
            SessionState.LoggedOut
    }

    fun onUnauthorized() {

        _sessionState.value =
            SessionState.LoggedOut
    }
}