package com.example.vocanapp.domain.usecase

import com.example.vocanapp.domain.model.WordList
import com.example.vocanapp.domain.repository.VocabularyRepository
import javax.inject.Inject

class GetWordsListUseCase @Inject constructor(
    private val vocabularyRepository: VocabularyRepository
) {

    suspend operator fun invoke(
        languageId: Int? = null,
        search: String? = null,
        page: Int? = null,
        pageSize: Int? = null,
        reviewLevel: Int? = null,
        due: Boolean? = null
    ): WordList {
        return vocabularyRepository.getWordList(
            languageId =  languageId,
            search = search,
            page = page,
            pageSize = pageSize,
            reviewLevel = reviewLevel,
            due = due
        )
    }
}