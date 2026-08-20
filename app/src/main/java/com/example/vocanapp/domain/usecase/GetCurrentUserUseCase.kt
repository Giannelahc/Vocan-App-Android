package com.example.vocanapp.domain.usecase

import com.example.vocanapp.domain.model.UserInfo
import com.example.vocanapp.domain.repository.UserRepository
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(): UserInfo {
        return userRepository.getCurrentUser()
    }
}