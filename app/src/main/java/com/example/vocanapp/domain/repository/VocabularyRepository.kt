package com.example.vocanapp.domain.repository

import com.example.vocanapp.domain.model.UserExample
import com.example.vocanapp.domain.model.Word
import com.example.vocanapp.domain.model.WordList
import com.example.vocanapp.domain.model.WordOption
import com.example.vocanapp.domain.model.WordSummary

interface VocabularyRepository {

    suspend fun getWordList(languageId: Int? = null, search: String? = null,
                            page: Int? = 1, pageSize: Int ?= 50, reviewLevel: Int? = null, due: Boolean? = null): WordList
    suspend fun getWordDetailById(wordId: Int): Word
    suspend fun addWord(word: String, languageId: Int, regenerate: Boolean): WordSummary
    suspend fun identifyWords(text: String, languageId: Int): List<WordOption>
    suspend fun deleteWord(wordId: Int): Unit
    suspend fun registerUserExamples(wordSenseId: Int, examples: List<String>): List<UserExample>
}