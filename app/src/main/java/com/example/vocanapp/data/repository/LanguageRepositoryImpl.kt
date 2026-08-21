package com.example.vocanapp.data.repository

import com.example.vocanapp.data.mapper.toDomain
import com.example.vocanapp.data.remote.LanguageApi
import com.example.vocanapp.domain.model.Language
import com.example.vocanapp.domain.repository.LanguageRepository
import javax.inject.Inject

class LanguageRepositoryImpl @Inject constructor(
    private val api: LanguageApi
) : LanguageRepository {


    override suspend fun getLanguages(): List<Language> {
        val languages = api.getLanguages()
        return languages.map { it.toDomain() }
    }


}