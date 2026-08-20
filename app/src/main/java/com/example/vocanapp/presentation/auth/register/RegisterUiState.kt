package com.example.vocanapp.presentation.auth.register

import com.example.vocanapp.domain.model.RegisterForm

data class RegisterUiState(
    val form: RegisterForm = RegisterForm(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val isRegistered: Boolean = false
)