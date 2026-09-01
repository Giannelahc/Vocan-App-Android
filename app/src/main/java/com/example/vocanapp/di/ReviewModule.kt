package com.example.vocanapp.di

import com.example.vocanapp.data.repository.ReviewRepositoryImpl
import com.example.vocanapp.domain.repository.ReviewRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ReviewModule {

    @Binds
    abstract fun bindReviewRepository(
        implementation: ReviewRepositoryImpl
    ): ReviewRepository
}