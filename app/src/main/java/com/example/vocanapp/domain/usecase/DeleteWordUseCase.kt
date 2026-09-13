package com.example.vocanapp.domain.usecase

import com.example.vocanapp.domain.repository.VocabularyRepository
import javax.inject.Inject

class DeleteWordUseCase @Inject constructor(
    private val vocabularyRepository: VocabularyRepository
) {

    suspend operator fun invoke(wordId: Int): Unit {
        return vocabularyRepository.deleteWord(wordId)
    }
}