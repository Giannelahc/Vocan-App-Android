package com.example.vocanapp.presentation.main.home

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.vocan.presentation.auth.login.HomeScreen
import com.example.vocan.presentation.auth.register.RegisterScreen
import com.example.vocanapp.presentation.auth.register.RegisterViewModel

@Composable
fun HomeRoute() {
    val viewModel: HomeViewModel = hiltViewModel()
    val uiState = viewModel.uiState

    HomeScreen(
        uiState = uiState
    )
}