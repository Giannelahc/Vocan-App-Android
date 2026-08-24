package com.example.vocanapp.domain.usecase

import com.example.vocanapp.domain.model.WordOption
import com.example.vocanapp.domain.repository.VocabularyRepository
import javax.inject.Inject

class IdentifyWordsUseCase @Inject constructor(
    private val vocabularyRepository: VocabularyRepository
) {
    suspend operator fun invoke(text: String, languageId: Int): List<WordOption> {
        return vocabularyRepository.identifyWords(text, languageId)
    }

}