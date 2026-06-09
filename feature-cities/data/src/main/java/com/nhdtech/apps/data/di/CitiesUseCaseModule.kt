package com.nhdtech.apps.data.di

import com.nhdtech.apps.domain.repository.CitiesRepository
import com.nhdtech.apps.domain.usecase.DeleteForecastFromDbUseCase
import com.nhdtech.apps.domain.usecase.GetForecastFromApiUseCase
import com.nhdtech.apps.domain.usecase.SaveForecastToDbUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CitiesUseCaseModule {
    @Singleton
    @Provides
    fun provideDeleteForecastFromDbUseCase(
        repository: CitiesRepository
    ): DeleteForecastFromDbUseCase {
        return DeleteForecastFromDbUseCase(repository)
    }
}