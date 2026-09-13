package com.example.vocanapp.presentation.main.vocabulary.detail

sealed interface VocabularyDetailEvent {

    data object ContentRegenerated : VocabularyDetailEvent

    data object WordDeleted : VocabularyDetailEvent
}