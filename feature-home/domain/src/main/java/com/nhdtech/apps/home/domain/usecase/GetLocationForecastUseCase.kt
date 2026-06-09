package com.nhdtech.apps.home.domain.usecase

import com.nhdtech.apps.home.domain.repository.ForecastRepository

class GetLocationForecastUseCase(private val repository: ForecastRepository) {
    suspend operator fun invoke(lat: Double, lon: Double) = repository.getForecast(lat, lon)
}