package com.nhdtech.apps.domain.repository

import com.nhdtech.apps.domain.model.WeatherForecast
import com.nhdtech.apps.domain.util.Resource
import com.nhdtech.apps.domain.util.ThemeMode
import kotlinx.coroutines.flow.Flow

interface CoreRepository {
    suspend fun getForecastFromApi(location: String): Resource<WeatherForecast>
    suspend fun saveForecastToDb(forecast: WeatherForecast)
    fun getTemperatureUnit(): Flow<String>
    fun getWindSpeedUnit(): Flow<String>
    fun getAtmosphericPressureUnit(): Flow<String>
    fun getThemeMode(): Flow<ThemeMode>
    suspend fun setTemperatureUnit(value: String)
    suspend fun setWindSpeedUnit(value: String)
    suspend fun setAtmosphericPressureUnit(value: String)
    suspend fun setThemeMode(mode: ThemeMode)
}