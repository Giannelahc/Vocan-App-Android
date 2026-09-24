package com.example.vocanapp.presentation.main.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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