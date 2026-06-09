package com.nhdtech.apps.home.data.di

import com.nhdtech.apps.home.domain.repository.ForecastRepository
import com.nhdtech.apps.home.domain.usecase.GetAllForecastsFromDbUseCase
import com.nhdtech.apps.home.domain.usecase.GetCurrentLocationUseCase
import com.nhdtech.apps.home.domain.usecase.GetHasAccessedLocationUseCase
import com.nhdtech.apps.home.domain.usecase.GetLocationForecastUseCase
import com.nhdtech.apps.home.domain.usecase.SetHasAccessedLocationUseCase
import com.nhdtech.apps.home.domain.util.LocationTracker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HomeUseCaseModule {
    @Singleton
    @Provides
    fun provideGetAllForecastsFromDbUseCase(
        repository: ForecastRepository
    ): GetAllForecastsFromDbUseCase {
        return GetAllForecastsFromDbUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetLocationForecastUseCase(
        repository: ForecastRepository
    ): GetLocationForecastUseCase {
        return GetLocationForecastUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetCurrentLocationUseCase(
        locationTracker: LocationTracker
    ): GetCurrentLocationUseCase {
        return GetCurrentLocationUseCase(locationTracker)
    }

    @Singleton
    @Provides
    fun provideGetHasAccessedLocationUseCase(
        repository: ForecastRepository
    ): GetHasAccessedLocationUseCase {
        return GetHasAccessedLocationUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideSetHasAccessedLocationUseCase(
        repository: ForecastRepository
    ): SetHasAccessedLocationUseCase {
        return SetHasAccessedLocationUseCase(repository)
    }
}