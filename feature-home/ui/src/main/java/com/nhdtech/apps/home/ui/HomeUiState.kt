package com.nhdtech.apps.home.ui

import com.nhdtech.apps.domain.model.WeatherForecast

data class HomeUiState(
    val savedForecasts: List<WeatherForecast> = emptyList(),
    val locationForecast: WeatherForecast? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val locationPermissionGranted: Boolean = false,
    val hasAccessedLocationBefore: Boolean = false,
    val temperatureUnit: String = "",
    val windSpeedUnit: String = "",
    val atmosphericPressureUnit: String = ""
)
