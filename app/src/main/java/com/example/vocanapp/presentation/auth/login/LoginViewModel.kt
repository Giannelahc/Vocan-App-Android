package com.example.vocanapp.presentation.auth.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vocanapp.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import android.util.Log
import com.example.vocanapp.data.session.SessionManager

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val sessionManager: SessionManager
) : ViewModel() {

    var uiState by mutableStateOf(LoginUiState())
        private set

    fun login(
        email: String,
        password: String
    ) {

        viewModelScope.launch {

            uiState = uiState.copy(
                isLoading = true,
                error = null
            )

            try {

                val result = loginUseCase(
                    email = email,
                    password = password
                )

                sessionManager.login(
                    result.accessToken
                )

                uiState = uiState.copy(
                    isLoading = false
                )

            } catch (e: Exception) {
                Log.e("LOGIN", "Login error", e)
                uiState = uiState.copy(
                    isLoading = false,
                    error = "Invalid email or password"
                )
            }
        }
    }
}