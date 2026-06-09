package com.nhdtech.apps.home.data.di

import com.nhdtech.apps.data.local.dao.ForecastDao
import com.nhdtech.apps.data.network.service.ForecastService
import com.nhdtech.apps.home.data.local.datastore.AppPreferences
import com.nhdtech.apps.home.data.repository.ForecastRepositoryImpl
import com.nhdtech.apps.home.domain.repository.ForecastRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HomeRepositoryModule {
    @Provides
    @Singleton
    fun provideHomeForecastRepository(
        forecastDao: ForecastDao,
        service: ForecastService,
        appPreferences: AppPreferences
    ): ForecastRepository {
        return ForecastRepositoryImpl(forecastDao, service, appPreferences)
    }
}