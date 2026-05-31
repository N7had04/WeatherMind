package com.nhdtech.apps.weathermind.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "forecast_day")
data class ForecastDay(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
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
