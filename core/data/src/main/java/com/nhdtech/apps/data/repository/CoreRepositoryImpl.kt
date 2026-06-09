package com.nhdtech.apps.data.repository

import com.nhdtech.apps.data.local.dao.ForecastDao
import com.nhdtech.apps.data.mapper.toDomain
import com.nhdtech.apps.data.mapper.toEntity
import com.nhdtech.apps.data.network.service.ForecastService
import com.nhdtech.apps.domain.model.WeatherForecast
import com.nhdtech.apps.domain.repository.CoreRepository
import com.nhdtech.apps.domain.util.Resource

class CoreRepositoryImpl(
    private val api: ForecastService,
    private val forecastDao: ForecastDao
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
}