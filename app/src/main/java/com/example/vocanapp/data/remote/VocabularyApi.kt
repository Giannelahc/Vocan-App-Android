package com.example.vocanapp.data.remote

import com.example.vocanapp.data.model.vocabulary.SentenceRequest
import com.example.vocanapp.data.model.vocabulary.VocabularyListResponse
import com.example.vocanapp.data.model.vocabulary.VocabularyWordResponse
import com.example.vocanapp.data.model.vocabulary.VocabularyWordSummaryResponse
import com.example.vocanapp.data.model.vocabulary.WordOptionResponse
import com.example.vocanapp.data.model.vocabulary.WordRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface VocabularyApi {

    @GET("vocabulary")
    suspend fun getWordList(
        @Query("language_id") languageId: Int? = null,
        @Query("search") search: String? = null,
        @Query("page") page: Int? = 1,
        @Query("page_size") pageSize: Int? = 50
    ): VocabularyListResponse

    @GET("vocabulary/{word_id}")
    suspend fun getWord(
        @Path("word_id") wordId: Int
    ): VocabularyWordResponse

    @POST("vocabulary/register-word")
    suspend fun registerWord(
        @Body request: WordRequest,
        @Query("regenerate") regenerate: Boolean
    ): VocabularyWordSummaryResponse

    @POST("vocabulary/identify")
    suspend fun identifyWordsFromASentence(
        @Body request: SentenceRequest
    ): List<WordOptionResponse>

    @DELETE("vocabulary/{word_id}")
    suspend fun deleteWord(
        @Path("word_id") wordId: Int
    ): Unit
}