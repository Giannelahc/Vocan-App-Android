package com.example.vocanapp.presentation.host

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vocanapp.presentation.auth.login.LoginRoute
import com.example.vocanapp.presentation.auth.register.RegisterRoute

@Composable
fun AuthNavHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        composable ("login") {
            LoginRoute (
                onRegisterClick = {
                    navController.navigate("register")
                }
            )
        }

        composable("register") {
            RegisterRoute (
                onRegisterSuccess = {
                    navController.navigate("login") {
                        popUpTo("register") {
                            inclusive = true
                        }
                    }
                },
                onLoginClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}