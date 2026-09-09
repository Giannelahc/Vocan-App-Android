package com.example.vocanapp.data.repository

import com.example.vocanapp.data.mapper.toDomain
import com.example.vocanapp.data.mapper.toRequest
import com.example.vocanapp.data.remote.PreferenceApi
import com.example.vocanapp.domain.model.Preference
import com.example.vocanapp.domain.repository.PreferenceRepository
import javax.inject.Inject

class PreferenceRepositoryImpl @Inject constructor(
    private val api: PreferenceApi
) : PreferenceRepository {
    override suspend fun getPreferences(): Preference {
        return api.getPreferences().toDomain()
    }

    override suspend fun registerPreference(preference: Preference): Unit {
        return api.registerPreference(preference.toRequest())
    }

}