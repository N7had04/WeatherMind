package com.nhdtech.apps.domain.repository

import com.nhdtech.apps.domain.model.WeatherForecast
import com.nhdtech.apps.domain.util.Resource

interface CitiesRepository {
    suspend fun deleteForecastFromDb(forecast: WeatherForecast)
}