package com.example.vocanapp.presentation.main.review.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.compose.runtime.getValue


@Composable
fun ReviewListRoute(
    navController: NavController,
    onReviewClick: (Int) -> Unit
) {

    val viewModel: ReviewListViewModel = hiltViewModel()

    val uiState = viewModel.uiState

    val reviewUpdated by navController
        .currentBackStackEntry!!
        .savedStateHandle
        .getStateFlow("review_updated", false)
        .collectAsState()

    LaunchedEffect(reviewUpdated) {

        if (reviewUpdated) {

            viewModel.refresh()

            navController
                .currentBackStackEntry
                ?.savedStateHandle
                ?.set("review_updated", false)
        }
    }

    ReviewListScreen(
        uiState = uiState,
        onBackClick = {
            navController.popBackStack()
        },
        onReviewClick = onReviewClick,
        onLoadMore = {
            viewModel.loadMore()
        }
    )
}