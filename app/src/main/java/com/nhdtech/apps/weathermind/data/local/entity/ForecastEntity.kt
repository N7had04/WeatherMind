package com.nhdtech.apps.weathermind.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "forecast")
data class ForecastEntity(
    @PrimaryKey
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
    val forecastDay: List<ForecastDay>
)
