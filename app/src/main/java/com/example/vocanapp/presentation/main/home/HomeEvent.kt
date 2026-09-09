package com.example.vocanapp.presentation.main.home

sealed interface HomeEvent {

    data object NavigateToProfile : HomeEvent
}