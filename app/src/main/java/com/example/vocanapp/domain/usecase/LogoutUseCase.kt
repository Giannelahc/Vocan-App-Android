package com.example.vocanapp.domain.usecase

import com.example.vocanapp.domain.repository.AuthRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(): Unit {

        authRepository.logout()
    }
}