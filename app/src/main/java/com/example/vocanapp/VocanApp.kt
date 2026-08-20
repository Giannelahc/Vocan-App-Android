package com.example.vocanapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.vocanapp.presentation.host.AuthNavHost
import com.example.vocanapp.presentation.host.MainNavHost
import com.example.vocanapp.presentation.session.SessionState
import com.example.vocanapp.presentation.session.SessionViewModel

@Composable
fun VocanApp() {

    val viewModel: SessionViewModel = hiltViewModel()

    val sessionState by viewModel.sessionState.collectAsState()

    when (sessionState) {

        SessionState.Loading -> {
            // SplashScreen()
        }

        SessionState.LoggedOut -> {
            AuthNavHost()
        }

        SessionState.LoggedIn -> {
            MainNavHost()
        }
    }
}