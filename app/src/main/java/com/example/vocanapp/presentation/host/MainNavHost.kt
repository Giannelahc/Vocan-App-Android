package com.example.vocanapp.presentation.host

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.vocanapp.presentation.main.MainNavigationBar
import com.example.vocanapp.presentation.main.home.HomeRoute
import com.example.vocanapp.presentation.main.vocabulary.VocabularyRoute
import com.example.vocanapp.presentation.main.vocabulary.detail.VocabularyDetailRoute
import com.example.vocanapp.presentation.main.vocabulary.wordidentification.IdentifyWordsRoute

@Composable
fun MainNavHost() {

    val navController = rememberNavController()

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
                HomeRoute()
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

            composable("profile") {
                // ProfileRoute()
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