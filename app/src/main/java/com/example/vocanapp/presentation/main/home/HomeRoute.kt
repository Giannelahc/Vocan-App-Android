package com.example.vocanapp.presentation.main.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.vocan.presentation.auth.login.HomeScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner

@Composable
fun HomeRoute(
    onNavigateToProfile: () -> Unit,
    onReviewClick: () -> Unit,
    onFlashcardsAvailabilityChanged: (Boolean) -> Unit
) {
    val viewModel: HomeViewModel = hiltViewModel()
    val uiState = viewModel.uiState

    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {

        val observer = LifecycleEventObserver { _, event ->

            if (event == Lifecycle.Event.ON_RESUME) {
                viewModel.refresh()
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

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
        uiState = uiState,
        onReviewClick = onReviewClick
    )

    LaunchedEffect(uiState.home?.flashcardsActive) {
        onFlashcardsAvailabilityChanged(uiState.home?.flashcardsActive == true)
    }
}