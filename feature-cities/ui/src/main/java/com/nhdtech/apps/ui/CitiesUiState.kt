package com.nhdtech.apps.ui

import com.nhdtech.apps.domain.model.WeatherForecast

data class CitiesUiState(
    val forecast: WeatherForecast? = null,
    val savedForecasts: List<WeatherForecast> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val searchCityText: String = "",
)
