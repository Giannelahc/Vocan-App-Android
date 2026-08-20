package com.example.vocanapp.presentation.auth.register

sealed interface RegisterEvent {

    data class NameChanged(
        val value: String
    ) : RegisterEvent

    data class LastnameChanged(
        val value: String
    ) : RegisterEvent

    data class UsernameChanged(
        val value: String
    ) : RegisterEvent

    data class EmailChanged(
        val value: String
    ) : RegisterEvent

    data class PasswordChanged(
        val value: String
    ) : RegisterEvent

    data object RegisterClicked : RegisterEvent

    data object LoginClicked : RegisterEvent
}