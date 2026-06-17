package com.nhdtech.apps.data.di

import com.nhdtech.apps.domain.repository.CoreRepository
import com.nhdtech.apps.domain.usecase.GetAtmosphericPressureUnitUseCase
import com.nhdtech.apps.domain.usecase.GetForecastFromApiUseCase
import com.nhdtech.apps.domain.usecase.GetTemperatureUnitUseCase
import com.nhdtech.apps.domain.usecase.GetThemeModeUseCase
import com.nhdtech.apps.domain.usecase.GetWindSpeedUnitUseCase
import com.nhdtech.apps.domain.usecase.SaveForecastToDbUseCase
import com.nhdtech.apps.domain.usecase.SetAtmosphericPressureUnitUseCase
import com.nhdtech.apps.domain.usecase.SetTemperatureUnitUseCase
import com.nhdtech.apps.domain.usecase.SetThemeModeUseCase
import com.nhdtech.apps.domain.usecase.SetWindSpeedUnitUseCase
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

    @Singleton
    @Provides
    fun provideGetTemperatureUnitUseCase(
        settingsRepository: CoreRepository
    ): GetTemperatureUnitUseCase {
        return GetTemperatureUnitUseCase(settingsRepository)
    }

    @Singleton
    @Provides
    fun provideSetTemperatureUnitUseCase(
        settingsRepository: CoreRepository
    ): SetTemperatureUnitUseCase {
        return SetTemperatureUnitUseCase(settingsRepository)
    }

    @Singleton
    @Provides
    fun provideGetWindSpeedUnitUseCase(
        settingsRepository: CoreRepository
    ): GetWindSpeedUnitUseCase {
        return GetWindSpeedUnitUseCase(settingsRepository)
    }

    @Singleton
    @Provides
    fun provideSetWindSpeedUnitUseCase(
        settingsRepository: CoreRepository
    ): SetWindSpeedUnitUseCase {
        return SetWindSpeedUnitUseCase(settingsRepository)
    }

    @Singleton
    @Provides
    fun provideGetAtmosphericPressureUnitUseCase(
        settingsRepository: CoreRepository
    ): GetAtmosphericPressureUnitUseCase {
        return GetAtmosphericPressureUnitUseCase(settingsRepository)
    }

    @Singleton
    @Provides
    fun provideSetAtmosphericPressureUnitUseCase(
        settingsRepository: CoreRepository
    ): SetAtmosphericPressureUnitUseCase {
        return SetAtmosphericPressureUnitUseCase(settingsRepository)
    }

    @Singleton
    @Provides
    fun provideGetThemeModeUseCase(
        settingsRepository: CoreRepository
    ): GetThemeModeUseCase {
        return GetThemeModeUseCase(settingsRepository)
    }

    @Singleton
    @Provides
    fun provideSetThemeModeUseCase(
        settingsRepository: CoreRepository
    ): SetThemeModeUseCase {
        return SetThemeModeUseCase(settingsRepository)
    }
}