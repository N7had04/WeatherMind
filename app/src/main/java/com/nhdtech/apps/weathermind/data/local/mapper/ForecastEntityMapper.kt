package com.nhdtech.apps.weathermind.data.local.mapper

import com.nhdtech.apps.weathermind.data.local.entity.ForecastDay
import com.nhdtech.apps.weathermind.data.local.entity.ForecastEntity
import com.nhdtech.apps.weathermind.data.local.entity.ForecastHour
import com.nhdtech.apps.weathermind.domain.model.WeatherForecast
import com.nhdtech.apps.weathermind.domain.model.WeatherForecastDay
import com.nhdtech.apps.weathermind.domain.model.WeatherForecastHour

fun ForecastHour.toDomain(): WeatherForecastHour {
    return WeatherForecastHour(
        conditionIcon = conditionIcon,
        temperatureCelsius = temperatureCelsius,
        temperatureFahrenheit = temperatureFahrenheit,
        time = time,
        windSpeedKph = windSpeedKph,
        windSpeedMph = windSpeedMph
    )
}

fun WeatherForecastHour.toEntity(): ForecastHour {
    return ForecastHour(
        conditionIcon = conditionIcon,
        temperatureCelsius = temperatureCelsius,
        temperatureFahrenheit = temperatureFahrenheit,
        time = time,
        windSpeedKph = windSpeedKph,
        windSpeedMph = windSpeedMph
    )
}

fun ForecastDay.toDomain(): WeatherForecastDay {
    return WeatherForecastDay(
        date = date,
        conditionIcon = conditionIcon,
        maxTempCelsius = maxTempCelsius,
        maxTempFahrenheit = maxTempFahrenheit,
        minTempCelsius = minTempCelsius,
        minTempFahrenheit = minTempFahrenheit,
        maxWindKph = maxWindKph,
        maxWindMph = maxWindMph,
        hours = hours.map { it.toDomain() }
    )
}

fun WeatherForecastDay.toEntity(): ForecastDay {
    return ForecastDay(
        date = date,
        conditionIcon = conditionIcon,
        maxTempCelsius = maxTempCelsius,
        maxTempFahrenheit = maxTempFahrenheit,
        minTempCelsius = minTempCelsius,
        minTempFahrenheit = minTempFahrenheit,
        maxWindKph = maxWindKph,
        maxWindMph = maxWindMph,
        hours = hours.map { it.toEntity() }
    )
}

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
        forecastDay = forecastDay.map { it.toDomain() }
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
        forecastDay = forecastDay.map { it.toEntity() }
    )
}