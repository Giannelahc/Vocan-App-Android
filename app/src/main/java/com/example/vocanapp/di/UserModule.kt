package com.example.vocanapp.di

import com.example.vocanapp.data.repository.UserRepositoryImpl
import com.example.vocanapp.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UserModule {

    @Binds
    abstract fun bindUserRepository(
        implementation: UserRepositoryImpl
    ): UserRepository
}