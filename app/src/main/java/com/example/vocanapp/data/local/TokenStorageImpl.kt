package com.example.vocanapp.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TokenStorageImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : TokenStorage {

    companion object {
        private val ACCESS_TOKEN = stringPreferencesKey("access_token")
    }

    override suspend fun saveToken(token: String) {
        dataStore.edit {
            preferences -> preferences[ACCESS_TOKEN] = token
        }
    }

    override suspend fun getToken(): String? {
        return dataStore.data
            .map { preferences ->
                preferences[ACCESS_TOKEN]
            }
            .first()
    }

    override suspend fun clearToken() {
        dataStore.edit { preferences ->
            preferences.remove(ACCESS_TOKEN)
        }
    }
}