package com.nhdtech.apps.domain.usecase

import com.nhdtech.apps.domain.repository.CoreRepository

class GetAtmosphericPressureUnitUseCase(private val coreRepository: CoreRepository) {
    operator fun invoke() = coreRepository.getAtmosphericPressureUnit()
}