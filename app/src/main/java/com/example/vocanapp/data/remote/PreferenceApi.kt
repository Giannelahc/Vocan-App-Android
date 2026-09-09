package com.example.vocanapp.data.remote

import com.example.vocanapp.data.model.preference.PreferenceRequest
import com.example.vocanapp.data.model.preference.PreferenceResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PreferenceApi {

    @GET("preference")
    suspend fun getPreferences(): PreferenceResponse

    @POST("preference")
    suspend fun registerPreference(
        @Body request: PreferenceRequest
    ): Unit

}