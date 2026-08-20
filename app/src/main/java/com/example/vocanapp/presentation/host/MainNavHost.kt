package com.example.vocanapp.presentation.host

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vocanapp.presentation.main.MainNavigationBar
import com.example.vocanapp.presentation.main.home.HomeRoute

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
                // VocabularyRoute()
            }

            composable("profile") {
                // ProfileRoute()
            }
        }
    }
}