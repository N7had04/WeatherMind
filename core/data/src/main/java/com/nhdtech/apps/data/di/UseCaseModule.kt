package com.nhdtech.apps.data.di

import com.nhdtech.apps.domain.repository.CoreRepository
import com.nhdtech.apps.domain.usecase.GetAtmosphericPressureUnitUseCase
import com.nhdtech.apps.domain.usecase.GetForecastFromApiUseCase
import com.nhdtech.apps.domain.usecase.GetTemperatureUnitUseCase
import com.nhdtech.apps.domain.usecase.GetThemeModeUseCase
import com.nhdtech.apps.domain.usecase.GetWindSpeedUnitUseCase
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
        coreRepository: CoreRepository
    ): GetForecastFromApiUseCase {
        return GetForecastFromApiUseCase(coreRepository)
    }

    @Singleton
    @Provides
    fun provideSaveForecastToDbUseCase(
        coreRepository: CoreRepository
    ): SaveForecastToDbUseCase {
        return SaveForecastToDbUseCase(coreRepository)
    }

    @Singleton
    @Provides
    fun provideGetTemperatureUnitUseCase(
        coreRepository: CoreRepository
    ): GetTemperatureUnitUseCase {
        return GetTemperatureUnitUseCase(coreRepository)
    }

    @Singleton
    @Provides
    fun provideGetWindSpeedUnitUseCase(
        coreRepository: CoreRepository
    ): GetWindSpeedUnitUseCase {
        return GetWindSpeedUnitUseCase(coreRepository)
    }

    @Singleton
    @Provides
    fun provideGetAtmosphericPressureUnitUseCase(
        coreRepository: CoreRepository
    ): GetAtmosphericPressureUnitUseCase {
        return GetAtmosphericPressureUnitUseCase(coreRepository)
    }

    @Singleton
    @Provides
    fun provideGetThemeModeUseCase(
        coreRepository: CoreRepository
    ): GetThemeModeUseCase {
        return GetThemeModeUseCase(coreRepository)
    }
}