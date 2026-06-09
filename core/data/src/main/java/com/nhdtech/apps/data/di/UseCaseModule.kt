package com.nhdtech.apps.data.di

import com.nhdtech.apps.domain.repository.CoreRepository
import com.nhdtech.apps.domain.usecase.GetForecastFromApiUseCase
import com.nhdtech.apps.domain.usecase.SaveForecastToDbUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Singleton
    @Provides
    fun provideGetForecastFromApiUseCase(
        repository: CoreRepository
    ): GetForecastFromApiUseCase {
        return GetForecastFromApiUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideSaveForecastToDbUseCase(
        repository: CoreRepository
    ): SaveForecastToDbUseCase {
        return SaveForecastToDbUseCase(repository)
    }
}