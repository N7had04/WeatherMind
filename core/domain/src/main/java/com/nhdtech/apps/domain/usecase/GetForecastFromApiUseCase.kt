package com.nhdtech.apps.domain.usecase

import com.nhdtech.apps.domain.repository.CoreRepository

class GetForecastFromApiUseCase(private val repository: CoreRepository) {
    suspend operator fun invoke(location: String) = repository.getForecastFromApi(location)
}