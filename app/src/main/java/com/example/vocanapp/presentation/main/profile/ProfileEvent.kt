package com.example.vocanapp.presentation.main.profile

sealed interface ProfileEvent {

    data object Logout : ProfileEvent
}