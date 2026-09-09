package com.example.vocanapp.presentation.main.home

import com.example.vocanapp.domain.model.HomeInfo

data class HomeUiState(
    val isLoading: Boolean = true,
    val home: HomeInfo? = null,
    val error: String? = null
)