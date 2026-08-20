package com.example.vocanapp.presentation.session

sealed interface SessionState {

    data object Loading : SessionState

    data object LoggedOut : SessionState

    data object LoggedIn : SessionState
}