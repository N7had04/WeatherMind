package com.nhdtech.apps.data.di

import com.nhdtech.apps.domain.repository.SettingsRepository
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
class SettingsUseCaseModule {
    @Singleton
    @Provides
    fun provideSetTemperatureUnitUseCase(
        settingsRepository: SettingsRepository
    ): SetTemperatureUnitUseCase {
        return SetTemperatureUnitUseCase(settingsRepository)
    }

    @Singleton
    @Provides
    fun provideSetWindSpeedUnitUseCase(
        settingsRepository: SettingsRepository
    ): SetWindSpeedUnitUseCase {
        return SetWindSpeedUnitUseCase(settingsRepository)
    }

    @Singleton
    @Provides
    fun provideSetAtmosphericPressureUnitUseCase(
        settingsRepository: SettingsRepository
    ): SetAtmosphericPressureUnitUseCase {
        return SetAtmosphericPressureUnitUseCase(settingsRepository)
    }

    @Singleton
    @Provides
    fun provideSetThemeModeUseCase(
        settingsRepository: SettingsRepository
    ): SetThemeModeUseCase {
        return SetThemeModeUseCase(settingsRepository)
    }
}