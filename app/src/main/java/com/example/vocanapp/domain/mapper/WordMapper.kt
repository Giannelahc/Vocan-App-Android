package com.example.vocanapp.domain.mapper

import com.example.vocanapp.domain.model.Flashcard
import com.example.vocanapp.domain.model.FlashcardTranslation
import com.example.vocanapp.domain.model.Word

fun Word.toFlashcard(): Flashcard =
    Flashcard(
        id = id,
        word = word,
        translations = senses.flatMap { it.translations.map { FlashcardTranslation(it.key, it.value) } },
        synonyms = senses.flatMap { it.synonyms.map { it.word }.distinct().take(3) },
        example = senses.mapNotNull { it.examples.firstOrNull()?.sentence}
    )

