package com.nhdtech.apps.domain.model

data class WeatherForecast(
    val locationName: String,
    val temperatureCelsius: Double,
    val temperatureFahrenheit: Double,
    val conditionText: String,
    val conditionIconUrl: String,
    val airQualityIndex: Double,
    val feelsLikeCelsius: Double,
    val feelsLikeFahrenheit: Double,
    val humidity: Int,
    val pressureMb: Double,
    val pressureIn: Double,
    val uvIndex: Double,
    val winDirection: String,
    val windKph: Double,
    val windMph: Double,
    val chanceOfRain: Int,
    val lastUpdatedEpoch: Long,
    val lastUpdated: String,
    val isCurrentLocation: Boolean,
    val forecastDay: List<WeatherForecastDay>
)
