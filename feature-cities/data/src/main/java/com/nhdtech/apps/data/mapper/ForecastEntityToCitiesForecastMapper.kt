package com.nhdtech.apps.data.mapper

import com.nhdtech.apps.data.local.entity.ForecastEntity
import com.nhdtech.apps.domain.model.CitiesForecast

fun ForecastEntity.toCitiesForecast(): CitiesForecast {
    return CitiesForecast(
        locationName = locationName,
        temperatureCelsius = temperatureCelsius,
        temperatureFahrenheit = temperatureFahrenheit,
        maxTempCelsius = forecastDay[0].maxTempCelsius,
        maxTempFahrenheit = forecastDay[0].maxTempFahrenheit,
        minTempCelsius = forecastDay[0].minTempCelsius,
        minTempFahrenheit = forecastDay[0].minTempFahrenheit,
        conditionText = conditionText,
        lastUpdated = lastUpdated,
        isCurrentLocation = isCurrentLocation
    )
}