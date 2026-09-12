package com.example.vocanapp.presentation.host

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.vocanapp.presentation.main.MainNavigationBar
import com.example.vocanapp.presentation.main.home.HomeRoute
import com.example.vocanapp.presentation.main.profile.ProfileRoute
import com.example.vocanapp.presentation.main.review.ReviewHomeRoute
import com.example.vocanapp.presentation.main.review.list.ReviewListRoute
import com.example.vocanapp.presentation.main.review.session.ReviewSessionRoute
import com.example.vocanapp.presentation.main.vocabulary.VocabularyRoute
import com.example.vocanapp.presentation.main.vocabulary.detail.VocabularyDetailRoute
import com.example.vocanapp.presentation.main.vocabulary.wordidentification.IdentifyWordsRoute

@Composable
fun MainNavHost(
    initialDestination: String? = null
) {

    val navController = rememberNavController()

    LaunchedEffect(initialDestination) {

        if (initialDestination == "review") {
            navController.navigate("review")
        }
    }

    Scaffold (
        bottomBar = {
            MainNavigationBar(
                navController = navController
            )
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {

            composable("home") {
                HomeRoute(
                    onNavigateToProfile = {
                        navController.navigate("profile") {
                            launchSingleTop = true
                        }
                    }
                )
            }

            composable("vocabulary") {
                VocabularyRoute (
                    onWordClick = { wordId ->
                        navController.navigate(
                            "vocabulary/$wordId"
                        )
                    },
                    onIdentifyWordsClick = {
                        navController.navigate("identify")
                    }
                )
            }

            composable("identify") {
                IdentifyWordsRoute (
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onWordsAdded = {
                        navController.popBackStack()
                    }
                )
            }

            composable("review") {
                ReviewHomeRoute(
                    onReviewClick = { reviewId ->
                        navController.navigate(
                            "review/$reviewId"
                        )
                    },
                    onViewAllClick = {
                        navController.navigate("review/all")
                    }
                )
            }

            composable("review/all") {
                ReviewListRoute(
                    navController = navController,
                    onReviewClick = { reviewId ->
                        navController.navigate("review/$reviewId")
                    }
                )
            }

            composable(
                route = "review/{reviewId}",
                arguments = listOf(
                    navArgument("reviewId") {
                        type = NavType.IntType
                    }
                )
            ) { backStackEntry ->

                val reviewId =
                    backStackEntry.arguments?.getInt("reviewId")
                        ?: return@composable

                ReviewSessionRoute(
                    reviewId = reviewId,
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onReviewFinished = {

                        navController.previousBackStackEntry
                            ?.savedStateHandle
                            ?.set("review_updated", true)

                        navController.popBackStack()
                    }
                )
            }

            composable("profile") {
                ProfileRoute(
                    onNotificationsClick = {
                        // navegación/configuración
                    },
                    onAppearanceClick = {
                        // navegación/configuración
                    }
                )
            }

            composable(
                route = "vocabulary/{wordId}",
                arguments = listOf(
                    navArgument ("wordId") {
                        type = NavType.IntType
                    }
                )
            ) { backStackEntry ->

                val wordId =
                    backStackEntry.arguments?.getInt("wordId")
                        ?: return@composable

                VocabularyDetailRoute(
                    wordId = wordId,
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}