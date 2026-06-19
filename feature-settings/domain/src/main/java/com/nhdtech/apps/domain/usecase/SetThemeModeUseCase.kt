package com.nhdtech.apps.domain.usecase

import com.nhdtech.apps.domain.repository.SettingsRepository
import com.nhdtech.apps.domain.util.ThemeMode

class SetThemeModeUseCase(private val settingsRepository: SettingsRepository) {
    suspend operator fun invoke(mode: ThemeMode) = settingsRepository.setThemeMode(mode)
}