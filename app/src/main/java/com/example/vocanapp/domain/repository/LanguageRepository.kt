package com.example.vocanapp.domain.repository

import com.example.vocanapp.domain.model.Language

interface LanguageRepository {

    suspend fun getLanguages(): List<Language>
}