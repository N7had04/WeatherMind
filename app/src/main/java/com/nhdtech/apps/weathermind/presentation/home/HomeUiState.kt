package com.nhdtech.apps.weathermind.presentation.home

import com.nhdtech.apps.weathermind.domain.model.WeatherForecast

data class HomeUiState(
    val forecasts: List<WeatherForecast> = emptyList(),
)