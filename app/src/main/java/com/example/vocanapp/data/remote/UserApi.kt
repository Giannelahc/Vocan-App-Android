package com.example.vocanapp.data.remote

import com.example.vocanapp.data.model.user.UserInfoResponse
import retrofit2.http.GET

interface UserApi {

    @GET("user/me")
    suspend fun getUserInfo(): UserInfoResponse

}