package com.nhdtech.apps.home.data.repository

import android.util.Log
import com.nhdtech.apps.data.local.dao.ForecastDao
import com.nhdtech.apps.data.mapper.toDomain
import com.nhdtech.apps.data.mapper.toEntity
import com.nhdtech.apps.data.network.service.ForecastService
import com.nhdtech.apps.domain.model.WeatherForecast
import com.nhdtech.apps.domain.util.Resource
import com.nhdtech.apps.home.data.local.datastore.HomePreferences
import com.nhdtech.apps.home.domain.repository.ForecastRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ForecastRepositoryImpl(
    private val forecastDao: ForecastDao,
    private val service: ForecastService,
    private val homePreferences: HomePreferences
): ForecastRepository {
    override fun getAllForecastsFromDb(): Flow<List<WeatherForecast>> {
        return forecastDao.getAllForecasts().map { it.map { entity -> entity.toDomain() } }
    }

    override suspend fun getForecast(
        lat: Double,
        lon: Double
    ): Resource<WeatherForecast> {
        try {
            val response = service.getForecast("$lat,$lon")
            if (response.isSuccessful) {
                response.body()?.let { forecastDto ->
                    return Resource.Success(forecastDto.toEntity().toDomain())
                }
            }
            return Resource.Error(response.message())
        } catch (e: Exception) {
            Log.e("ForecastRepo", "Exception: ${e.message}", e)
            return Resource.Error(e.message ?: "An error occurred")
        }
    }

    override fun getHasAccessedLocation(): Flow<Boolean> {
        return homePreferences.hasAccessedLocation
    }

    override suspend fun setHasAccessedLocation(value: Boolean) {
        homePreferences.setHasAccessedLocation(value)
    }
}
