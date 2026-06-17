package com.nhdtech.apps.home.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.nhdtech.apps.domain.model.WeatherForecastDay
import com.nhdtech.apps.home.ui.HomeUiState

@Composable
fun ForecastRow(
    modifier: Modifier = Modifier,
    day: WeatherForecastDay,
    state: HomeUiState
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = day.date,
            color = MaterialTheme.colorScheme.onSecondary,
            style = MaterialTheme.typography.bodyLarge
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = "https:${day.conditionIcon}",
                contentDescription = "Weather icon",
                modifier = Modifier.size(48.dp)
            )

            Text(
                text = "${if (state.windSpeedUnit == "km/h") day.maxWindKph else day.maxWindMph}" +
                        if (state.windSpeedUnit == "km/h") "km/h" else "mph",
                color = MaterialTheme.colorScheme.onSecondary,
                style = MaterialTheme.typography.labelMedium
            )
        }

        Text(
            text = "L:${if (state.temperatureUnit == "°C") day.minTempCelsius.toInt() else day.minTempFahrenheit.toInt()}°",
            color = MaterialTheme.colorScheme.onSecondary,
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = "H:${if (state.temperatureUnit == "°C") day.maxTempCelsius.toInt() else day.maxTempFahrenheit.toInt()}°",
            color = MaterialTheme.colorScheme.onSecondary,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
