package com.example.vocanapp.di

import com.example.vocanapp.data.repository.AuthRepositoryImpl
import com.example.vocanapp.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthModule {

    @Binds
    abstract fun bindAuthRepository(
        implementation: AuthRepositoryImpl
    ): AuthRepository
}