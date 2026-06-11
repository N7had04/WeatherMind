package com.nhdtech.apps.domain.usecase

import com.nhdtech.apps.domain.repository.CitiesRepository

class DeleteForecastFromDbUseCase(private val repository: CitiesRepository) {
    suspend operator fun invoke(locationName: String) = repository.deleteForecastFromDb(locationName)
}