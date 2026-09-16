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
import com.example.vocanapp.domain.usecase.GetHomeInfoUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val getHomeInfoUseCase: GetHomeInfoUseCase
) : ViewModel() {

    var uiState by mutableStateOf(HomeUiState())
        private set

    private val _events = MutableSharedFlow<HomeEvent>()
    val events = _events.asSharedFlow()

    fun refresh() {

        viewModelScope.launch {

            try {

                val user = getCurrentUserUseCase()
                val home = getHomeInfoUseCase()

                uiState = uiState.copy(
                    isLoading = false,
                    home = home,
                    error = null
                )

                if (user.preference == null) {

                    _events.emit(
                        HomeEvent.NavigateToProfile
                    )
                }

            } catch (e: Exception) {

                Log.e(
                    "LOAD HOME ERROR",
                    "Load home error",
                    e
                )

                uiState = uiState.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }
}