package com.nhdtech.apps.data.di

import com.nhdtech.apps.domain.repository.CitiesRepository
import com.nhdtech.apps.domain.usecase.DeleteForecastFromDbUseCase
import com.nhdtech.apps.domain.usecase.GetAllCitiesForecastsFromDbUseCase
import com.nhdtech.apps.domain.usecase.SearchCitiesUseCase
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

    @Singleton
    @Provides
    fun provideGetAllCitiesForecastsFromDbUseCase(
        repository: CitiesRepository
    ): GetAllCitiesForecastsFromDbUseCase {
        return GetAllCitiesForecastsFromDbUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideSearchCitiesUseCase(
        repository: CitiesRepository
    ): SearchCitiesUseCase {
        return SearchCitiesUseCase(repository)
    }
}