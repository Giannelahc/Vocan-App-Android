package com.example.vocanapp.presentation.auth.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import android.util.Log
import com.example.vocanapp.domain.usecase.RegisterUseCase

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    var uiState by mutableStateOf(RegisterUiState())
        private set

    fun onEvent(event: RegisterEvent) {

        when (event) {

            is RegisterEvent.NameChanged -> {
                uiState = uiState.copy(
                    form = uiState.form.copy(
                        name = event.value
                    )
                )
            }

            is RegisterEvent.LastnameChanged -> {
                uiState = uiState.copy(
                    form = uiState.form.copy(
                        lastname = event.value
                    )
                )
            }

            is RegisterEvent.UsernameChanged -> {
                uiState = uiState.copy(
                    form = uiState.form.copy(
                        username = event.value
                    )
                )
            }

            is RegisterEvent.EmailChanged -> {
                uiState = uiState.copy(
                    form = uiState.form.copy(
                        email = event.value
                    )
                )
            }

            is RegisterEvent.PasswordChanged -> {
                uiState = uiState.copy(
                    form = uiState.form.copy(
                        password = event.value
                    )
                )
            }

            RegisterEvent.RegisterClicked -> {
                register()
            }

            RegisterEvent.LoginClicked -> {
                // La navegación no debería ocurrir aquí.
            }
        }
    }

    fun register() {

        viewModelScope.launch {

            uiState = uiState.copy(
                isLoading = true,
                error = null
            )

            try {

                val result = registerUseCase(
                    uiState.form
                )

                uiState = uiState.copy(
                    isLoading = false,
                    isRegistered = true
                )

            } catch (e: Exception) {
                Log.e("REGISTER", "Register error", e)
                uiState = uiState.copy(
                    isLoading = false,
                    error = "Register failed"
                )
            }
        }
    }
}