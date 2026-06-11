package com.nhdtech.apps.domain.usecase

import com.nhdtech.apps.domain.repository.CoreRepository

class SetAtmosphericPressureUnitUseCase(private val coreRepository: CoreRepository) {
    suspend operator fun invoke(value: String) = coreRepository.setAtmosphericPressureUnit(value)
}