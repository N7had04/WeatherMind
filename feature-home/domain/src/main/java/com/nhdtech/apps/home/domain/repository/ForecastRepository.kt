package com.nhdtech.apps.home.domain.repository

import com.nhdtech.apps.domain.model.WeatherForecast
import com.nhdtech.apps.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface ForecastRepository {
    fun getAllForecastsFromDb(): Flow<List<WeatherForecast>>
    suspend fun getForecast(lat: Double, lon: Double): Resource<WeatherForecast>
    fun getHasAccessedLocation(): Flow<Boolean>
    suspend fun setHasAccessedLocation(value: Boolean)
}