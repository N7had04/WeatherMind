package com.nhdtech.apps.weathermind.domain.usecase

import com.nhdtech.apps.weathermind.domain.model.WeatherForecast
import com.nhdtech.apps.weathermind.domain.repository.ForecastRepository
import javax.inject.Inject

class DeleteForecastFromDbUseCase @Inject constructor(private val repository: ForecastRepository) {
    suspend fun execute(forecast: WeatherForecast) = repository.deleteForecastFromDb(forecast)
}