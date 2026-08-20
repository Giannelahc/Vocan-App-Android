package com.example.vocan.presentation.auth.login


import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.vocanapp.presentation.main.home.HomeUiState

@Composable
fun HomeScreen(
    uiState: HomeUiState
) {

    when {

        uiState.isLoading -> {

            CircularProgressIndicator()
        }

        uiState.error != null -> {

            Text(
                text = uiState.error,
                color = MaterialTheme.colorScheme.error
            )
        }

        uiState.user != null -> {

            Text(
                text = "Hello, ${uiState.user.username}!",
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}