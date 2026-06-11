package com.nhdtech.apps.domain.usecase

import com.nhdtech.apps.domain.repository.CoreRepository

class GetTemperatureUnitUseCase(private val coreRepository: CoreRepository) {
    operator fun invoke() = coreRepository.getTemperatureUnit()
}