package com.example.vocanapp.domain.usecase

import javax.inject.Inject

class AddSelectedWordsUseCase @Inject constructor(
    private val addWordUseCase: AddWordUseCase
) {

    suspend operator fun invoke(
        words: List<String>,
        languageId: Int
    ) {

        words.forEach { word ->

            addWordUseCase(
                word = word,
                languageId = languageId
            )
        }
    }
}