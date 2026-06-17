package com.nhdtech.apps.domain.repository

import com.nhdtech.apps.domain.model.CitiesForecast
import com.nhdtech.apps.domain.model.City
import com.nhdtech.apps.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface CitiesRepository {
    suspend fun deleteForecastFromDb(locationName: String)
    fun getAllForecastsFromDb(): Flow<List<CitiesForecast>>
    suspend fun updateSortOrder(forecasts: List<CitiesForecast>)
    suspend fun searchCities(query: String): Resource<List<City>>
}