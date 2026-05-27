package com.nhdtech.apps.weathermind.domain.usecase

import com.nhdtech.apps.weathermind.domain.model.WeatherForecast
import com.nhdtech.apps.weathermind.domain.repository.ForecastRepository

class SaveForecastToDbUseCase(private val repository: ForecastRepository) {
    suspend fun execute(forecast: WeatherForecast) = repository.saveForecastToDb(forecast)
}