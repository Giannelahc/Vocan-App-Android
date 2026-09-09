package com.example.vocanapp.domain.usecase

import com.example.vocanapp.domain.model.HomeInfo
import com.example.vocanapp.domain.repository.UserRepository
import javax.inject.Inject

class GetHomeInfoUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(): HomeInfo {
        return userRepository.getHomeInfo()
    }
}