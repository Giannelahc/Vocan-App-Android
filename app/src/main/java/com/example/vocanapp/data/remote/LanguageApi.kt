package com.example.vocanapp.data.remote

import com.example.vocanapp.data.model.vocabulary.LanguageResponse
import retrofit2.http.GET

interface LanguageApi {

    @GET("languages")
    suspend fun getLanguages(): List<LanguageResponse>

}