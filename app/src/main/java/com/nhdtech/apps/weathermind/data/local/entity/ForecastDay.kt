package com.nhdtech.apps.weathermind.data.local.entity

data class ForecastDay(
    val date: String,
    val conditionIcon: String,
    val maxTempCelsius: Double,
    val maxTempFahrenheit: Double,
    val minTempCelsius: Double,
    val minTempFahrenheit: Double,
    val maxWindKph: Double,
    val maxWindMph: Double,
    val hours: List<ForecastHour>
)
