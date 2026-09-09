package com.example.vocanapp.presentation.main.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.vocan.presentation.auth.login.HomeScreen
import com.example.vocan.presentation.auth.register.RegisterScreen
import com.example.vocanapp.presentation.auth.register.RegisterViewModel

@Composable
fun HomeRoute(
    onNavigateToProfile: () -> Unit
) {
    val viewModel: HomeViewModel = hiltViewModel()
    val uiState = viewModel.uiState

    LaunchedEffect(Unit) {

        viewModel.events.collect { event ->

            when (event) {

                HomeEvent.NavigateToProfile -> {
                    onNavigateToProfile()
                }
            }
        }
    }

    HomeScreen(
        uiState = uiState
    )
}