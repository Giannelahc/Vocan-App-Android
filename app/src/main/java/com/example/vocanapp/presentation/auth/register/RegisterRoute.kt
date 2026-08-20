package com.example.vocanapp.presentation.auth.register

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.vocan.presentation.auth.register.RegisterScreen

@Composable
fun RegisterRoute(
    onRegisterSuccess: () -> Unit,
    onLoginClick: () -> Unit
) {
    val viewModel: RegisterViewModel = hiltViewModel()
    val uiState = viewModel.uiState

    RegisterScreen(
        uiState = uiState,
        onEvent = viewModel::onEvent,
        onRegisterSuccess = onRegisterSuccess,
        onLoginClick = onLoginClick
    )
}