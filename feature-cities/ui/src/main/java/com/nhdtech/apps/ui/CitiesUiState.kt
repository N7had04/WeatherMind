package com.nhdtech.apps.ui

import com.nhdtech.apps.domain.model.CitiesForecast
import com.nhdtech.apps.domain.model.City
import com.nhdtech.apps.domain.model.WeatherForecast

data class CitiesUiState(
    val forecast: WeatherForecast? = null,
    val savedForecasts: List<CitiesForecast> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val searchCityText: String = "",
    val temperatureUnit: String = "",
    val windSpeedUnit: String = "",
    val atmosphericPressureUnit: String = "",
    val cities: List<City> = emptyList()
)
