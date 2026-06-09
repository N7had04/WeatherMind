package com.nhdtech.apps.domain.model

data class WeatherForecastDay(
    val date: String,
    val conditionIcon: String,
    val maxTempCelsius: Double,
    val maxTempFahrenheit: Double,
    val minTempCelsius: Double,
    val minTempFahrenheit: Double,
    val maxWindKph: Double,
    val maxWindMph: Double,
    val hours: List<WeatherForecastHour>
)