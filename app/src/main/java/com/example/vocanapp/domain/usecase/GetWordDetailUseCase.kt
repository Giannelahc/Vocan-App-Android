package com.example.vocanapp.domain.usecase

import com.example.vocanapp.domain.model.Word
import com.example.vocanapp.domain.repository.VocabularyRepository
import javax.inject.Inject

class GetWordDetailUseCase @Inject constructor(
    private val vocabularyRepository: VocabularyRepository
) {

    suspend operator fun invoke(
        wordId: Int
    ): Word {
        return vocabularyRepository.getWordDetailById(
            wordId =  wordId
        )
    }
}