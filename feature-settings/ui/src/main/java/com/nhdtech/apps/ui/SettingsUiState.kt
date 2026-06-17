package com.nhdtech.apps.ui

import com.nhdtech.apps.domain.util.ThemeMode

data class SettingsUiState(
    val temperatureUnit: String = "",
    val windSpeedUnit: String = "",
    val atmosphericPressureUnit: String = "",
    val themeMode: ThemeMode = ThemeMode.SYSTEM
)
