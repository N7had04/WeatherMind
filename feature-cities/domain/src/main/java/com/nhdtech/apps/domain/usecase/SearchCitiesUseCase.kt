package com.nhdtech.apps.domain.usecase

import com.nhdtech.apps.domain.repository.CitiesRepository

class SearchCitiesUseCase(private val repository: CitiesRepository) {
    suspend operator fun invoke(query: String) = repository.searchCities(query)
}