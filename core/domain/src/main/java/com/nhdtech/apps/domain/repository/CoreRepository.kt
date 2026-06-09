package com.nhdtech.apps.domain.repository

import com.nhdtech.apps.domain.model.WeatherForecast
import com.nhdtech.apps.domain.util.Resource

interface CoreRepository {
    suspend fun getForecastFromApi(location: String): Resource<WeatherForecast>
    suspend fun saveForecastToDb(forecast: WeatherForecast)
}