package com.example.vocanapp.domain.usecase

import com.example.vocanapp.domain.model.UserExample
import com.example.vocanapp.domain.repository.VocabularyRepository
import javax.inject.Inject

class AddUserExamplesUseCase @Inject constructor(
    private val vocabularyRepository: VocabularyRepository
) {

    suspend operator fun invoke(wordSenseId: Int, examples: List<String>): List<UserExample> {
        return vocabularyRepository.registerUserExamples(wordSenseId, examples)
    }
}