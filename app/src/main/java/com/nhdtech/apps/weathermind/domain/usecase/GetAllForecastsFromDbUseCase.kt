package com.nhdtech.apps.weathermind.domain.usecase

import com.nhdtech.apps.weathermind.domain.repository.ForecastRepository

class GetAllForecastsFromDbUseCase(private val repository: ForecastRepository) {
    suspend fun execute() = repository.getAllForecastsFromDb()
}