package com.nhdtech.apps.home.domain.usecase

import com.nhdtech.apps.home.domain.repository.ForecastRepository

class SetHasAccessedLocationUseCase(private val repository: ForecastRepository) {
    suspend operator fun invoke(value: Boolean) = repository.setHasAccessedLocation(value)
}