package com.example.vocanapp.domain.repository

import com.example.vocanapp.domain.model.Word
import com.example.vocanapp.domain.model.WordList
import com.example.vocanapp.domain.model.WordSummary

interface VocabularyRepository {

    suspend fun getWordList(languageId: Int? = null, search: String? = null, page: Int? = 1, pageSize: Int ?= 50): WordList
    suspend fun getWordDetailById(wordId: Int): Word
    suspend fun addWord(word: String, languageId: Int): WordSummary
}