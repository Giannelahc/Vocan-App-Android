package com.example.vocanapp.presentation.main.home

import com.example.vocanapp.domain.model.UserInfo

data class HomeUiState(
    val isLoading: Boolean = true,
    val user: UserInfo? = null,
    val error: String? = null
)