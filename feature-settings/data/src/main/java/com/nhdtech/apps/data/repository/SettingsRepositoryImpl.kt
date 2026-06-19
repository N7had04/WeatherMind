package com.nhdtech.apps.data.repository

import com.nhdtech.apps.data.local.datastore.AppPreferences
import com.nhdtech.apps.domain.repository.SettingsRepository
import com.nhdtech.apps.domain.util.ThemeMode

class SettingsRepositoryImpl(
    private val appPreferences: AppPreferences
): SettingsRepository {
    override suspend fun setTemperatureUnit(value: String) {
        appPreferences.setTemperatureUnit(value)
    }

    override suspend fun setWindSpeedUnit(value: String) {
        appPreferences.setWindSpeedUnit(value)
    }

    override suspend fun setAtmosphericPressureUnit(value: String) {
        appPreferences.setAtmosphericPressureUnit(value)
    }

    override suspend fun setThemeMode(mode: ThemeMode) {
        appPreferences.setThemeMode(mode)
    }
}