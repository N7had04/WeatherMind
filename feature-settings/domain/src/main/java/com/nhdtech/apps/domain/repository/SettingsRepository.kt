package com.nhdtech.apps.domain.repository

import com.nhdtech.apps.domain.util.ThemeMode

interface SettingsRepository {
    suspend fun setTemperatureUnit(value: String)
    suspend fun setWindSpeedUnit(value: String)
    suspend fun setAtmosphericPressureUnit(value: String)
    suspend fun setThemeMode(mode: ThemeMode)
}