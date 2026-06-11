package com.nhdtech.apps.domain.usecase

import com.nhdtech.apps.domain.repository.CoreRepository

class SetWindSpeedUnitUseCase(private val coreRepository: CoreRepository) {
    suspend operator fun invoke(value: String) = coreRepository.setWindSpeedUnit(value)
}