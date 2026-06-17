package com.nhdtech.apps.domain.usecase

import com.nhdtech.apps.domain.repository.CoreRepository
import com.nhdtech.apps.domain.util.ThemeMode

class SetThemeModeUseCase(private val coreRepository: CoreRepository) {
    suspend operator fun invoke(mode: ThemeMode) = coreRepository.setThemeMode(mode)
}