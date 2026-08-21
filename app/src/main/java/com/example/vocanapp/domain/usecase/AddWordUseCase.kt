package com.example.vocanapp.domain.usecase

import com.example.vocanapp.domain.model.WordSummary
import com.example.vocanapp.domain.repository.VocabularyRepository
import javax.inject.Inject

class AddWordUseCase @Inject constructor(
    private val vocabularyRepository: VocabularyRepository
) {

    suspend operator fun invoke(word: String, languageId: Int): WordSummary {
        return vocabularyRepository.addWord(word, languageId)
    }
}