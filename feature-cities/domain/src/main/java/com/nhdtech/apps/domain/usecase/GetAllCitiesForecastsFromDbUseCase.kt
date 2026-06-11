package com.nhdtech.apps.domain.usecase

import com.nhdtech.apps.domain.model.CitiesForecast
import com.nhdtech.apps.domain.repository.CitiesRepository
import kotlinx.coroutines.flow.Flow

class GetAllCitiesForecastsFromDbUseCase(private val repository: CitiesRepository) {
    operator fun invoke(): Flow<List<CitiesForecast>> {
        return repository.getAllForecastsFromDb()
    }
}