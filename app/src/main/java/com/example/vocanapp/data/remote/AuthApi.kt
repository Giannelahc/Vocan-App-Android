package com.example.vocanapp.data.remote

import com.example.vocanapp.data.model.login.LoginRequest
import com.example.vocanapp.data.model.login.LoginResponse
import com.example.vocanapp.data.model.register.RegisterUserRequest
import com.example.vocanapp.data.model.register.RegisterUserResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse

    @POST("auth/register")
    suspend fun registerUser(
        @Body request: RegisterUserRequest
    ): RegisterUserResponse

    @POST("auth/logout")
    suspend fun logout(): Unit
}