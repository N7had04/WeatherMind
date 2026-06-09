package com.nhdtech.apps.domain.usecase

import com.nhdtech.apps.domain.model.WeatherForecast
import com.nhdtech.apps.domain.repository.CitiesRepository

class DeleteForecastFromDbUseCase(private val repository: CitiesRepository) {
    suspend operator fun invoke(forecast: WeatherForecast) = repository.deleteForecastFromDb(forecast)
}