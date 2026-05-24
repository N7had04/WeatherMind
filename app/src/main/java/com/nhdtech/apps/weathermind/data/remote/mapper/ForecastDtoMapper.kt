package com.nhdtech.apps.weathermind.data.remote.mapper

import com.nhdtech.apps.weathermind.data.local.entity.ForecastDay
import com.nhdtech.apps.weathermind.data.local.entity.ForecastEntity
import com.nhdtech.apps.weathermind.data.local.entity.ForecastHour
import com.nhdtech.apps.weathermind.data.remote.dto.ForecastDto
import com.nhdtech.apps.weathermind.data.remote.dto.Forecastday
import com.nhdtech.apps.weathermind.data.remote.dto.Hour

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
        forecastDay = forecast.forecastday.map { it.toEntity() }
    )
}