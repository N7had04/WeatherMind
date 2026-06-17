package com.nhdtech.apps.data.repository

import com.nhdtech.apps.data.local.dao.ForecastDao
import com.nhdtech.apps.data.mapper.toCitiesForecast
import com.nhdtech.apps.data.mapper.toDomain
import com.nhdtech.apps.data.network.service.AutoCompleteService
import com.nhdtech.apps.domain.model.CitiesForecast
import com.nhdtech.apps.domain.model.City
import com.nhdtech.apps.domain.repository.CitiesRepository
import com.nhdtech.apps.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CitiesRepositoryImpl(
    private val forecastDao: ForecastDao,
    private val service: AutoCompleteService
): CitiesRepository {
    override suspend fun deleteForecastFromDb(locationName: String) {
        forecastDao.deleteCurrentForecast(locationName)
    }

    override fun getAllForecastsFromDb(): Flow<List<CitiesForecast>> {
        return forecastDao.getAllForecasts().map { list ->
            list.map { it.toCitiesForecast() }
        }
    }

    override suspend fun updateSortOrder(forecasts: List<CitiesForecast>) {
        forecasts.forEachIndexed { index, forecast ->
            forecastDao.updateSortOrder(forecast.locationName, index)
        }
    }

    override suspend fun searchCities(query: String): Resource<List<City>> {
        return try {
            val response = service.searchCities(query)
            val body = response.body()
            if (response.isSuccessful && body != null) {
                Resource.Success(body.map { it.toDomain() })
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occurred")
        }
    }
}