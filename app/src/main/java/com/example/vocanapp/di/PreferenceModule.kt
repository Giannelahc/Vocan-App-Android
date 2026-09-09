package com.example.vocanapp.di

import com.example.vocanapp.data.repository.PreferenceRepositoryImpl
import com.example.vocanapp.domain.repository.PreferenceRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferenceModule {

    @Binds
    abstract fun bindPreferenceRepository(
        implementation: PreferenceRepositoryImpl
    ): PreferenceRepository
}