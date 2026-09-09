package com.example.vocanapp.domain.usecase

import com.example.vocanapp.domain.model.Preference
import com.example.vocanapp.domain.repository.PreferenceRepository
import javax.inject.Inject

class RegisterPreferenceUseCase @Inject constructor(
    private val preferenceRepository: PreferenceRepository
) {

    suspend operator fun invoke(preference: Preference): Unit {
        return preferenceRepository.registerPreference(preference)
    }
}