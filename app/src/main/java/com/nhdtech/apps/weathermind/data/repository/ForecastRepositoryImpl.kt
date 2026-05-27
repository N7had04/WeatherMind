package com.nhdtech.apps.weathermind.data.repository

import com.nhdtech.apps.weathermind.data.local.dao.ForecastDao
import com.nhdtech.apps.weathermind.data.local.mapper.toDomain
import com.nhdtech.apps.weathermind.data.local.mapper.toEntity
import com.nhdtech.apps.weathermind.data.remote.api.ForecastApi
import com.nhdtech.apps.weathermind.data.remote.mapper.toEntity
import com.nhdtech.apps.weathermind.domain.model.WeatherForecast
import com.nhdtech.apps.weathermind.domain.repository.ForecastRepository
import com.nhdtech.apps.weathermind.utils.Resource

class ForecastRepositoryImpl(
    private val api: ForecastApi,
    private val forecastDao: ForecastDao
): ForecastRepository {
    override suspend fun getForecastFromApi(location: String): Resource<WeatherForecast> {
        try {
            val response = api.getForecast(location)
            if (response.isSuccessful) {
                response.body()?.let { forecastDto ->
                    return Resource.Success(forecastDto.toEntity().toDomain())
                }
            }
            return Resource.Error(response.message())
        } catch (e: Exception) {
            return Resource.Error(e.message ?: "An error occurred")
        }
    }

    override suspend fun getAllForecastsFromDb(): List<WeatherForecast> {
        return forecastDao.getAllForecasts().map { it.toDomain() }
    }

    override suspend fun saveForecastToDb(forecast: WeatherForecast) {
        forecastDao.insertCurrentForecast(forecast.toEntity())
    }

    override suspend fun deleteForecastFromDb(forecast: WeatherForecast) {
        forecastDao.deleteCurrentForecast(forecast.toEntity())
    }
}