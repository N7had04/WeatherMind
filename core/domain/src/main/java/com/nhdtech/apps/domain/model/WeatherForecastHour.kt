package com.nhdtech.apps.domain.model

data class WeatherForecastHour(
    val conditionIcon: String,
    val temperatureCelsius: Double,
    val temperatureFahrenheit: Double,
    val time: String,
    val windSpeedKph: Double,
    val windSpeedMph: Double
)