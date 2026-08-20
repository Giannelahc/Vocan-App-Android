package com.example.vocanapp.presentation.main.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import android.util.Log
import com.example.vocanapp.domain.usecase.GetCurrentUserUseCase
import com.example.vocanapp.domain.usecase.RegisterUseCase
import com.example.vocanapp.presentation.auth.register.RegisterEvent
import com.example.vocanapp.presentation.auth.register.RegisterUiState

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {

    var uiState by mutableStateOf(HomeUiState())
        private set

    init {
        loadUser()
    }

    private fun loadUser() {

        viewModelScope.launch {

            uiState = uiState.copy(
                isLoading = true,
                error = null
            )

            try {

                val user = getCurrentUserUseCase()

                uiState = uiState.copy(
                    isLoading = false,
                    user = user
                )

            } catch (e: Exception) {
                Log.e("LOAD USER ERROR", "Load user error", e)
                uiState = uiState.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }
}