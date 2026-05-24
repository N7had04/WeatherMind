package com.nhdtech.apps.weathermind.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastDto(
    @SerialName("current")
    val current: Current,
    @SerialName("forecast")
    val forecast: Forecast,
    @SerialName("location")
    val location: Location
)