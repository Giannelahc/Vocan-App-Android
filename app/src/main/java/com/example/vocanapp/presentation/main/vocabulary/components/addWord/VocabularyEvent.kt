package com.example.vocanapp.presentation.main.vocabulary.components.addWord

sealed interface VocabularyEvent {

    data object WordAdded : VocabularyEvent
}