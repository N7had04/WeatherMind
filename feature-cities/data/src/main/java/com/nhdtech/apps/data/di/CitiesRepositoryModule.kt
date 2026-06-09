package com.nhdtech.apps.data.di

import com.nhdtech.apps.data.local.dao.ForecastDao
import com.nhdtech.apps.data.network.service.ForecastService
import com.nhdtech.apps.data.repository.CitiesRepositoryImpl
import com.nhdtech.apps.domain.repository.CitiesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CitiesRepositoryModule {
    @Singleton
    @Provides
    fun provideCitiesRepository(
        forecastDao: ForecastDao
    ): CitiesRepository {
        return CitiesRepositoryImpl(forecastDao)
    }
}