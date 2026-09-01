package com.example.vocanapp.presentation.main.review.session

sealed interface ReviewSessionEvent {

    data object ReviewFinished : ReviewSessionEvent
}