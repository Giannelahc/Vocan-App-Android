package com.example.vocanapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.vocanapp.data.local.TokenStorage
import com.example.vocanapp.data.local.TokenStorageImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {

    private val Context.dataStore by preferencesDataStore(
        name = "vocan_preferences"
    )

    @Provides
    @Singleton
    fun provideDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> {
        return context.dataStore
    }

    @Provides
    @Singleton
    fun provideTokenStorage(
        dataStore: DataStore<Preferences>
    ): TokenStorage {
        return TokenStorageImpl(dataStore)
    }
}