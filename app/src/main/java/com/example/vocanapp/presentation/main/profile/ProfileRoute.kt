package com.example.vocanapp.presentation.main.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.vocanapp.presentation.main.profile.component.LearningLanguagesBottomSheet
import com.example.vocanapp.presentation.main.profile.component.NativeLanguageBottomSheet

@Composable
fun ProfileRoute(
    onNotificationsClick: () -> Unit,
    onAppearanceClick: () -> Unit
) {

    val viewModel: ProfileViewModel = hiltViewModel()

    val uiState = viewModel.uiState

    var showNativeLanguageSheet by rememberSaveable {
        mutableStateOf(false)
    }

    var showLearningLanguagesSheet by rememberSaveable {
        mutableStateOf(false)
    }

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                ProfileEvent.PreferencesSaved -> {
                    snackbarHostState.showSnackbar(
                        message = "Preferences saved successfully"
                    )
                }
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        ProfileScreen(
            uiState = uiState,

            onNativeLanguageClick = {
                showNativeLanguageSheet = true
            },

            onLearningLanguagesClick = {
                showLearningLanguagesSheet = true
            },

            onNotificationsClick = onNotificationsClick,

            onAppearanceClick = onAppearanceClick,

            onLogoutClick = {
                viewModel.logout()
            },

            onSavePreferences = {
                viewModel.savePreferences()
            }
        )
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ){ data ->
            Snackbar(
                snackbarData = data,
                containerColor = Color(0xFF307F8A),
                contentColor = Color.White
            )
        }
    }

    if (showNativeLanguageSheet) {

        NativeLanguageBottomSheet(
            selectedLanguage = uiState.nativeLanguage,
            languages = uiState.availableLanguages,

            onLanguageSelected = { language ->

                viewModel.updateNativeLanguage(language)

                showNativeLanguageSheet = false
            },

            onDismiss = {
                showNativeLanguageSheet = false
            }
        )
    }

    if (showLearningLanguagesSheet) {

        LearningLanguagesBottomSheet(
            selectedLanguages = uiState.learningLanguages,
            languages = uiState.availableLanguages,

            onDone = { languages ->

                viewModel.updateLearningLanguages(languages)

                showLearningLanguagesSheet = false
            },

            onDismiss = {
                showLearningLanguagesSheet = false
            }
        )
    }
}