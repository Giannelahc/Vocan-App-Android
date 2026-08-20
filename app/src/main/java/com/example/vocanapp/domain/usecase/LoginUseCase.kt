package com.example.vocanapp.domain.usecase

import com.example.vocanapp.domain.model.LoginResult
import com.example.vocanapp.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(
        email: String,
        password: String
    ): LoginResult {

        return authRepository.login(
            email = email,
            password = password
        )
    }
}