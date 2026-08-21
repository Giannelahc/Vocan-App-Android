package com.example.vocanapp.di

import com.example.vocanapp.data.repository.VocabularyRepositoryImpl
import com.example.vocanapp.domain.repository.VocabularyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class VocabularyModule {

    @Binds
    abstract fun bindVocabularyRepository(
        implementation: VocabularyRepositoryImpl
    ): VocabularyRepository
}