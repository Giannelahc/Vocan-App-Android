package com.example.vocanapp.presentation.session

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vocanapp.data.session.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(
    private val sessionManager: SessionManager
) : ViewModel() {

    val sessionState =
        sessionManager.sessionState

    init {
        viewModelScope.launch {
            sessionManager.initialize()
        }
    }

    fun logout() {
        viewModelScope.launch {
            sessionManager.logout()
        }
    }
}