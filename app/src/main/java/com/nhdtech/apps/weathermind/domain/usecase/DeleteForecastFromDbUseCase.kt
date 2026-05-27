package com.nhdtech.apps.weathermind.domain.usecase

import com.nhdtech.apps.weathermind.domain.model.WeatherForecast
import com.nhdtech.apps.weathermind.domain.repository.ForecastRepository

class DeleteForecastFromDbUseCase(private val repository: ForecastRepository) {
    suspend fun execute(forecast: WeatherForecast) = repository.deleteForecastFromDb(forecast)
}