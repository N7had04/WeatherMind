package com.nhdtech.apps.weathermind.data.local.mapper

import com.nhdtech.apps.weathermind.data.local.entity.ForecastEntity
import com.nhdtech.apps.weathermind.domain.model.WeatherForecast

fun ForecastEntity.toDomain(): WeatherForecast {
    return WeatherForecast(
        locationName = locationName,
        temperatureCelsius = temperatureCelsius,
        temperatureFahrenheit = temperatureFahrenheit,
        conditionText = conditionText,
        conditionIconUrl = conditionIconUrl,
        airQualityIndex = airQualityIndex,
        feelsLikeCelsius = feelsLikeCelsius,
        feelsLikeFahrenheit = feelsLikeFahrenheit,
        humidity = humidity,
        pressureMb = pressureMb,
        pressureIn = pressureIn,
        uvIndex = uvIndex,
        winDirection = winDirection,
        windKph = windKph,
        windMph = windMph,
        chanceOfRain = chanceOfRain,
        forecastDay = forecastDay
    )
}

fun WeatherForecast.toEntity(): ForecastEntity {
    return ForecastEntity(
        locationName = locationName,
        temperatureCelsius = temperatureCelsius,
        temperatureFahrenheit = temperatureFahrenheit,
        conditionText = conditionText,
        conditionIconUrl = conditionIconUrl,
        airQualityIndex = airQualityIndex,
        feelsLikeCelsius = feelsLikeCelsius,
        feelsLikeFahrenheit = feelsLikeFahrenheit,
        humidity = humidity,
        pressureMb = pressureMb,
        pressureIn = pressureIn,
        uvIndex = uvIndex,
        winDirection = winDirection,
        windKph = windKph,
        windMph = windMph,
        chanceOfRain = chanceOfRain,
        forecastDay = forecastDay
    )
}