package com.nhdtech.apps.domain.usecase

import com.nhdtech.apps.domain.repository.SettingsRepository

class SetAtmosphericPressureUnitUseCase(private val settingsRepository: SettingsRepository) {
    suspend operator fun invoke(value: String) = settingsRepository.setAtmosphericPressureUnit(value)
}