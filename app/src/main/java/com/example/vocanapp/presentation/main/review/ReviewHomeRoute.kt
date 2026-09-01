package com.example.vocanapp.presentation.main.review

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner

@Composable
fun ReviewHomeRoute(
    onReviewClick: (Int) -> Unit,
    onViewAllClick: () -> Unit
) {
    val viewModel: ReviewHomeViewModel = hiltViewModel()
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

    ReviewHomeScreen(
        uiState = uiState,
        onReviewClick = onReviewClick,
        onViewAllClick = onViewAllClick
    )
}