package com.nhdtech.apps.domain.usecase

import com.nhdtech.apps.domain.model.WeatherForecast
import com.nhdtech.apps.domain.repository.CoreRepository

class SaveForecastToDbUseCase(private val repository: CoreRepository) {
    suspend operator fun invoke(forecast: WeatherForecast) = repository.saveForecastToDb(forecast)
}