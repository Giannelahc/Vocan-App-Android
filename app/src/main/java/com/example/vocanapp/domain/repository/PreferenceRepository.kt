package com.example.vocanapp.domain.repository

import com.example.vocanapp.domain.model.Preference

interface PreferenceRepository {

    suspend fun getPreferences(): Preference
    suspend fun registerPreference(preference: Preference): Unit
}