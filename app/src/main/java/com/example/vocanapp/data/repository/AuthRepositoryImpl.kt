package com.example.vocanapp.data.repository

import com.example.vocanapp.data.model.login.LoginRequest
import com.example.vocanapp.data.model.register.RegisterUserRequest
import com.example.vocanapp.data.remote.AuthApi
import com.example.vocanapp.data.remote.UserApi
import com.example.vocanapp.domain.model.LoginResult
import com.example.vocanapp.domain.model.RegisterResult
import com.example.vocanapp.domain.model.User
import com.example.vocanapp.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val userApi: UserApi
) : AuthRepository {

    override suspend fun login(
        email: String,
        password: String
    ): LoginResult {

        val response = authApi.login(
            LoginRequest(
                email = email,
                password = password
            )
        )
        return LoginResult(
            accessToken = response.accessToken,
            refreshToken = response.refreshToken,
            tokenType = response.tokenType
        )
    }

    override suspend fun register(user: User): RegisterResult {
        val response = authApi.registerUser(
            RegisterUserRequest(user.name, user.lastname,
                user.username, user.email, user.password))

        return RegisterResult(response.id, response.email)
    }

    override suspend fun logout() {
        userApi.logout()
    }
}