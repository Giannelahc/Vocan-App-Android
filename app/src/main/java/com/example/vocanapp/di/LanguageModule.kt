package com.example.vocanapp.di

import com.example.vocanapp.data.repository.LanguageRepositoryImpl
import com.example.vocanapp.domain.repository.LanguageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LanguageModule {

    @Binds
    abstract fun bindLanguageRepository(
        implementation: LanguageRepositoryImpl
    ): LanguageRepository
}