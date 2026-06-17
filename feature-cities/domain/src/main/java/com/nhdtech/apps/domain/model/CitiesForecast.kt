package com.nhdtech.apps.domain.model

data class CitiesForecast(
    val locationName: String,
    val temperatureCelsius: Double,
    val temperatureFahrenheit: Double,
    val maxTempCelsius: Double,
    val maxTempFahrenheit: Double,
    val minTempCelsius: Double,
    val minTempFahrenheit: Double,
    val conditionText: String,
    val lastUpdated: String,
    val isCurrentLocation: Boolean
)
