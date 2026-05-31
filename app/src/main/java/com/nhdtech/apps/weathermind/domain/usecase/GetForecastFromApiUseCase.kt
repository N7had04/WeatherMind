package com.nhdtech.apps.weathermind.domain.usecase

import com.nhdtech.apps.weathermind.domain.repository.ForecastRepository
import javax.inject.Inject

class GetForecastFromApiUseCase @Inject constructor(private val repository: ForecastRepository) {
    suspend fun execute(location: String) = repository.getForecastFromApi(location)
}