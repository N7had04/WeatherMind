package com.nhdtech.apps.weathermind.domain.usecase

import com.nhdtech.apps.weathermind.domain.repository.ForecastRepository

class GetForecastFromApiUseCase(private val repository: ForecastRepository) {
    suspend fun execute(location: String) = repository.getForecastFromApi(location)
}