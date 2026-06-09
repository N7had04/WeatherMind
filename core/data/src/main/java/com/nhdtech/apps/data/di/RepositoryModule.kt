package com.nhdtech.apps.data.di

import com.nhdtech.apps.data.local.dao.ForecastDao
import com.nhdtech.apps.data.network.service.ForecastService
import com.nhdtech.apps.data.repository.CoreRepositoryImpl
import com.nhdtech.apps.domain.repository.CoreRepository
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
    fun provideCoreRepository(
        service: ForecastService,
        forecastDao: ForecastDao
    ): CoreRepository {
        return CoreRepositoryImpl(service, forecastDao)
    }
}