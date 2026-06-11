package com.nhdtech.apps.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
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
    @ColumnInfo(defaultValue = "0")
    val lastUpdatedEpoch: Long,
    @ColumnInfo(defaultValue = "")
    val lastUpdated: String,
    val forecastDay: List<ForecastDay>
)
