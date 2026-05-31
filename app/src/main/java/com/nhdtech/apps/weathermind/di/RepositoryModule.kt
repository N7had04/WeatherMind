package com.nhdtech.apps.weathermind.di

import com.nhdtech.apps.weathermind.data.local.dao.ForecastDao
import com.nhdtech.apps.weathermind.data.remote.api.ForecastApi
import com.nhdtech.apps.weathermind.data.repository.ForecastRepositoryImpl
import com.nhdtech.apps.weathermind.domain.repository.ForecastRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideForecastRepository(
        api: ForecastApi,
        forecastDao: ForecastDao
    ): ForecastRepository {
        return ForecastRepositoryImpl(api, forecastDao)
    }
}