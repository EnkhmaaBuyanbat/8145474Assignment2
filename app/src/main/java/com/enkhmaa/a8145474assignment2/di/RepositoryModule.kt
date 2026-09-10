package com.enkhmaa.a8145474assignment2.di

import com.enkhmaa.a8145474assignment2.repository.TechnologyRepository
import com.enkhmaa.a8145474assignment2.repository.TechnologyRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindTechnologyRepository(
        technologyRepositoryImpl: TechnologyRepositoryImpl
    ): TechnologyRepository
}
