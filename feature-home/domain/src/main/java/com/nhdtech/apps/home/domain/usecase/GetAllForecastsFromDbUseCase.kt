package com.nhdtech.apps.home.domain.usecase

import com.nhdtech.apps.home.domain.repository.ForecastRepository

class GetAllForecastsFromDbUseCase(private val repository: ForecastRepository) {
    operator fun invoke() = repository.getAllForecastsFromDb()
}