package com.example.vocanapp.presentation.auth.login

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.vocan.presentation.auth.login.LoginScreen

@Composable
fun LoginRoute(
    onRegisterClick: () -> Unit
) {
    val viewModel: LoginViewModel = hiltViewModel()
    val uiState = viewModel.uiState

    LoginScreen(
        uiState = uiState,
        onLoginClick = { email, password ->
            viewModel.login(email, password)
        },
        onRegisterClick = onRegisterClick
    )
}