package com.nhdtech.apps.data.repository

import com.nhdtech.apps.data.local.dao.ForecastDao
import com.nhdtech.apps.data.local.datastore.AppPreferences
import com.nhdtech.apps.data.mapper.toDomain
import com.nhdtech.apps.data.mapper.toEntity
import com.nhdtech.apps.data.network.service.ForecastService
import com.nhdtech.apps.domain.model.WeatherForecast
import com.nhdtech.apps.domain.repository.CoreRepository
import com.nhdtech.apps.domain.util.Resource
import com.nhdtech.apps.domain.util.ThemeMode
import kotlinx.coroutines.flow.Flow

class CoreRepositoryImpl(
    private val api: ForecastService,
    private val forecastDao: ForecastDao,
    private val appPreferences: AppPreferences
) : CoreRepository {
    override suspend fun getForecastFromApi(location: String): Resource<WeatherForecast> {
        return try {
            val response = api.getForecast(location)
            val body = response.body()
            if (response.isSuccessful && body != null) {
                Resource.Success(body.toEntity().toDomain())
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occurred")
        }
    }

    override suspend fun saveForecastToDb(forecast: WeatherForecast) {
        forecastDao.insertCurrentForecast(forecast.toEntity())
    }

    override fun getTemperatureUnit(): Flow<String> {
        return appPreferences.temperatureUnit
    }

    override fun getWindSpeedUnit(): Flow<String> {
        return appPreferences.windSpeedUnit
    }

    override fun getAtmosphericPressureUnit(): Flow<String> {
        return appPreferences.atmosphericPressureUnit
    }

    override fun getThemeMode(): Flow<ThemeMode> {
        return appPreferences.themeMode
    }

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