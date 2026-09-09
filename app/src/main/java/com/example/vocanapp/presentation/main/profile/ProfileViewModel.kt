package com.example.vocanapp.presentation.main.profile

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vocanapp.data.session.SessionManager
import com.example.vocanapp.domain.model.Language
import com.example.vocanapp.domain.model.Preference
import com.example.vocanapp.domain.usecase.GetCurrentUserUseCase
import com.example.vocanapp.domain.usecase.GetLanguagesListUseCase
import com.example.vocanapp.domain.usecase.LogoutUseCase
import com.example.vocanapp.domain.usecase.RegisterPreferenceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserPreferencesUseCase: GetCurrentUserUseCase,
    private val getLanguagesListUseCase: GetLanguagesListUseCase,
    private val updateUserPreferencesUseCase: RegisterPreferenceUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val sessionManager: SessionManager
) : ViewModel() {

    var uiState by mutableStateOf(ProfileUiState())
        private set

    private val _events = MutableSharedFlow<ProfileEvent>()
    val events = _events.asSharedFlow()

    init {
        loadProfile()
    }

    private fun loadProfile() {

        viewModelScope.launch {

            uiState = uiState.copy(
                isLoading = true,
                error = null
            )

            try {

                val preferences = getUserPreferencesUseCase()
                val languages = getLanguagesListUseCase()

                uiState = uiState.copy(
                    isLoading = false,
                    username = preferences.username,
                    email = preferences.email,
                    preferenceId = preferences.preference?.id,
                    nativeLanguage = preferences.preference?.nativeLanguage,
                    learningLanguages = preferences.preference?.learningLanguages ?: emptyList(),
                    availableLanguages = languages
                )

            } catch (e: Exception) {

                Log.e(
                    "PROFILE",
                    "Error loading profile",
                    e
                )

                uiState = uiState.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun updateNativeLanguage(
        language: Language
    ) {
        uiState = uiState.copy(
            nativeLanguage = language
        )
    }

    fun updateLearningLanguages(
        languages: List<Language>
    ) {
        uiState = uiState.copy(
            learningLanguages = languages
        )
    }

    fun savePreferences() {

        if (uiState.nativeLanguage == null) {
            return
        }

        if (uiState.learningLanguages.isEmpty()) {
            return
        }

        viewModelScope.launch {

            uiState = uiState.copy(
                isSaving = true,
                error = null
            )

            try {

                uiState.nativeLanguage?.let {
                    updateUserPreferencesUseCase(Preference(
                        id = uiState.preferenceId,
                        nativeLanguage = it,
                        learningLanguages = uiState.learningLanguages)
                    )
                }

                uiState = uiState.copy(
                    isSaving = false
                )

            } catch (e: Exception) {

                Log.e(
                    "PROFILE",
                    "Error saving preferences",
                    e
                )

                uiState = uiState.copy(
                    isSaving = false,
                    error = e.message
                )
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            try {
                logoutUseCase()

                sessionManager.logout()

            } catch (e: Exception) {
                Log.e(
                    "PROFILE",
                    "Error logging out",
                    e
                )
            }
        }
    }
}