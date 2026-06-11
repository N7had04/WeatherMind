package com.nhdtech.apps.domain.usecase

import com.nhdtech.apps.domain.repository.CoreRepository

class SetTemperatureUnitUseCase(private val coreRepository: CoreRepository) {
    suspend operator fun invoke(value: String) = coreRepository.setTemperatureUnit(value)
}