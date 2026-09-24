package com.example.vocanapp.presentation.main.flashcard

sealed interface FlashcardsEvent {

    data object ReviewCompleted : FlashcardsEvent

    data object Completed : FlashcardsEvent

    data object Restarted : FlashcardsEvent
}