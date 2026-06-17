package com.nhdtech.apps.data.mapper

import com.nhdtech.apps.data.local.entity.ForecastDay
import com.nhdtech.apps.data.local.entity.ForecastEntity
import com.nhdtech.apps.data.local.entity.ForecastHour
import com.nhdtech.apps.data.network.dto.ForecastDto
import com.nhdtech.apps.data.network.dto.Forecastday
import com.nhdtech.apps.data.network.dto.Hour


fun Hour.toEntity(): ForecastHour {
    return ForecastHour(
        conditionIcon = condition.icon,
        temperatureCelsius = tempC,
        temperatureFahrenheit = tempF,
        time = time,
        windSpeedKph = windKph,
        windSpeedMph = windMph
    )
}

fun Forecastday.toEntity(): ForecastDay {
    return ForecastDay(
        date = date,
        conditionIcon = day.condition.icon,
        maxTempCelsius = day.maxtempC,
        maxTempFahrenheit = day.maxtempF,
        minTempCelsius = day.mintempC,
        minTempFahrenheit = day.mintempF,
        maxWindKph = day.maxwindKph,
        maxWindMph = day.maxwindMph,
        hours = hour.map { it.toEntity() }
    )
}

fun ForecastDto.toEntity(): ForecastEntity {
    return ForecastEntity(
        locationName = location.name,
        temperatureCelsius = current.tempC,
        temperatureFahrenheit = current.tempF,
        conditionText = current.condition.text,
        conditionIconUrl = current.condition.icon,
        airQualityIndex = current.airQuality.pm10,
        feelsLikeCelsius = current.feelslikeC,
        feelsLikeFahrenheit = current.feelslikeF,
        humidity = current.humidity,
        pressureMb = current.pressureMb,
        pressureIn = current.pressureIn,
        uvIndex = current.uv,
        winDirection = current.windDir,
        windKph = current.windKph,
        windMph = current.windMph,
        chanceOfRain = current.chanceOfRain,
        lastUpdatedEpoch = current.lastUpdatedEpoch * 1000L,
        lastUpdated = current.lastUpdated,
        isCurrentLocation = false,
        forecastDay = forecast.forecastday.map { it.toEntity() }
    )
}