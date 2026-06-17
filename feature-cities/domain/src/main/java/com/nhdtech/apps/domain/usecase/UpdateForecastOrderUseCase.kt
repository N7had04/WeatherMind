package com.nhdtech.apps.domain.usecase

import com.nhdtech.apps.domain.model.CitiesForecast
import com.nhdtech.apps.domain.repository.CitiesRepository

class UpdateForecastOrderUseCase(private val repository: CitiesRepository) {
    suspend operator fun invoke(forecasts: List<CitiesForecast>) {
        repository.updateSortOrder(forecasts)
    }
}