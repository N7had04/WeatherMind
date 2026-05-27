package com.nhdtech.apps.weathermind.domain.repository

import com.nhdtech.apps.weathermind.domain.model.WeatherForecast
import com.nhdtech.apps.weathermind.utils.Resource

interface ForecastRepository {
    suspend fun getForecastFromApi(location: String): Resource<WeatherForecast>
    suspend fun getAllForecastsFromDb(): List<WeatherForecast>
    suspend fun saveForecastToDb(forecast: WeatherForecast)
    suspend fun deleteForecastFromDb(forecast: WeatherForecast)
}