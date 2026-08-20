package com.example.vocanapp.domain.usecase

import com.example.vocanapp.domain.model.RegisterForm
import com.example.vocanapp.domain.model.RegisterResult
import com.example.vocanapp.domain.model.User
import com.example.vocanapp.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(
        registerForm: RegisterForm
    ): RegisterResult {

        return authRepository.register(
            User(name = registerForm.name, lastname = registerForm.lastname,
                username = registerForm.username, email = registerForm.email, password = registerForm.password)
        )
    }
}