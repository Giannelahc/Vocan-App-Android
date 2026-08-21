package com.example.vocanapp.domain.usecase

import com.example.vocanapp.domain.model.Language
import com.example.vocanapp.domain.repository.LanguageRepository
import javax.inject.Inject

class GetLanguagesListUseCase @Inject constructor(
    private val languageRepository: LanguageRepository
) {

    suspend operator fun invoke(): List<Language> {
        return languageRepository.getLanguages()
    }
}