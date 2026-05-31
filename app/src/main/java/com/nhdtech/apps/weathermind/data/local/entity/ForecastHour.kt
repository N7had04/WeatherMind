package com.nhdtech.apps.weathermind.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "forecast_hour")
data class ForecastHour(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val conditionIcon: String,
    val temperatureCelsius: Double,
    val temperatureFahrenheit: Double,
    val time: String,
    val windSpeedKph: Double,
    val windSpeedMph: Double
)
