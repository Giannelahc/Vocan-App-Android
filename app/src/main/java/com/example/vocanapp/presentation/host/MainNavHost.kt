package com.example.vocanapp.presentation.host

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.vocanapp.presentation.main.MainNavigationBar
import com.example.vocanapp.presentation.main.flashcard.FlashcardRoute
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

    var flashcardsActive by rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(initialDestination) {

        when (initialDestination) {

            "review" -> {
                navController.navigate("review")
            }

            "flashcards" -> {
                navController.navigate("flashcards")
            }
        }
    }

    Scaffold (
        bottomBar = {
            MainNavigationBar(
                navController = navController,
                flashcardsActive = flashcardsActive
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
                    },
                    onReviewClick = {
                        navController.navigate("review")
                    },
                    onFlashcardsAvailabilityChanged = {
                        flashcardsActive = it
                    }
                )
            }

            composable("vocabulary") { backStackEntry ->
                VocabularyRoute(
                    navBackStackEntry = backStackEntry,
                    onWordClick = { wordId ->
                        navController.navigate(
                            "vocabulary/$wordId"
                        )
                    },
                    onIdentifyWordsClick = { word, languageId ->
                        val route = if (
                            !word.isNullOrBlank() &&
                            languageId != null
                        ) {
                            "identify?word=${Uri.encode(word)}&languageId=$languageId"
                        } else {
                            "identify"
                        }

                        navController.navigate(route)
                    }
                )
            }

            composable(
                route = "identify?word={word}&languageId={languageId}",
                arguments = listOf(
                    navArgument("word") {
                        type = NavType.StringType
                        defaultValue = ""
                    },
                    navArgument("languageId") {
                        type = NavType.IntType
                        defaultValue = -1
                    }
                )
            ) { backStackEntry ->

                val word = backStackEntry.arguments
                    ?.getString("word")
                    .orEmpty()

                val languageId = backStackEntry.arguments
                    ?.getInt("languageId")
                    ?.takeIf { it != -1 }

                IdentifyWordsRoute(
                    initialText = word.ifBlank { null },
                    initialLanguageId = languageId,
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onWordsAdded = {
                        navController.previousBackStackEntry
                            ?.savedStateHandle
                            ?.set("words_added", true)

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

            composable("flashcards") {
                FlashcardRoute()
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
                    navArgument("wordId") {
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
                    },

                    onContentRegenerated = {

                        navController.previousBackStackEntry
                            ?.savedStateHandle
                            ?.set("content_regenerated", true)

                        navController.popBackStack()
                    },
                    onWordDeleted = {
                        navController.previousBackStackEntry
                            ?.savedStateHandle
                            ?.set("word_deleted", true)

                        navController.popBackStack()
                    }
                )
            }
        }
    }
}