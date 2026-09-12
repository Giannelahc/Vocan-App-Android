package com.example.vocanapp.data.remote

import com.example.vocanapp.data.model.user.HomeInfoResponse
import com.example.vocanapp.data.model.user.UserInfoResponse
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {

    @GET("user/me")
    suspend fun getUserInfo(): UserInfoResponse

    @GET("user/home-summary")
    suspend fun getHomeSummary(): HomeInfoResponse

    @POST("auth/logout")
    suspend fun logout()

}