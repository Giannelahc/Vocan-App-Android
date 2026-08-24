package com.example.vocanapp.presentation.main.vocabulary.wordidentification

sealed interface WordIdentificationEvent {

    data object WordsAdded : WordIdentificationEvent
}