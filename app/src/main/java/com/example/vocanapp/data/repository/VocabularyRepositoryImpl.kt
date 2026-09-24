package com.example.vocanapp.data.repository

import com.example.vocanapp.data.mapper.toDomain
import com.example.vocanapp.data.model.vocabulary.SentenceRequest
import com.example.vocanapp.data.model.vocabulary.UserExampleRequest
import com.example.vocanapp.data.model.vocabulary.WordRequest
import com.example.vocanapp.data.remote.VocabularyApi
import com.example.vocanapp.domain.model.UserExample
import com.example.vocanapp.domain.model.Word
import com.example.vocanapp.domain.model.WordList
import com.example.vocanapp.domain.model.WordOption
import com.example.vocanapp.domain.model.WordSummary
import com.example.vocanapp.domain.repository.VocabularyRepository
import javax.inject.Inject

class VocabularyRepositoryImpl @Inject constructor(
    private val api: VocabularyApi
) : VocabularyRepository {

    override suspend fun getWordList(
        languageId: Int?,
        search: String?,
        page: Int?,
        pageSize: Int?,
        reviewLevel: Int?,
        due: Boolean?
    ): WordList {
        val vocabularyList = api.getWordList(languageId, search, page, pageSize, reviewLevel, due)
        return vocabularyList.toDomain()
    }

    override suspend fun getWordDetailById(wordId: Int): Word {
        val vocabularyWord = api.getWord(wordId)
        return vocabularyWord.toDomain()
    }

    override suspend fun addWord(
        word: String,
        languageId: Int,
        regenerate: Boolean
    ): WordSummary {
        val savedWord = api.registerWord(WordRequest(word, languageId), regenerate)
        return savedWord.toDomain()
    }

    override suspend fun identifyWords(
        text: String,
        languageId: Int
    ): List<WordOption> {
        val wordOptions = api.identifyWordsFromASentence(SentenceRequest(text, languageId))
        return wordOptions.map { it.toDomain() }
    }

    override suspend fun deleteWord(wordId: Int) {
        return api.deleteWord(wordId)
    }

    override suspend fun registerUserExamples(wordSenseId: Int, examples: List<String>): List<UserExample> {
        return api.registerUserExamples(wordSenseId, UserExampleRequest(examples)).map { it.toDomain() }
    }


}