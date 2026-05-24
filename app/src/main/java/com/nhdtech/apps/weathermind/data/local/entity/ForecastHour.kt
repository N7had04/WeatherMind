package com.nhdtech.apps.weathermind.data.local.entity

data class ForecastHour(
    val conditionIcon: String,
    val temperatureCelsius: Double,
    val temperatureFahrenheit: Double,
    val time: String,
    val windSpeedKph: Double,
    val windSpeedMph: Double
)
