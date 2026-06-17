package com.nhdtech.apps.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.nhdtech.apps.domain.model.WeatherForecast
import com.nhdtech.apps.ui.CitiesUiState
import com.nhdtech.apps.ui.theme.Error

@Composable
fun SearchResultScreen(
    modifier: Modifier = Modifier,
    state: CitiesUiState,
    onNavigateBack: () -> Unit,
    onAddForecast: (WeatherForecast) -> Unit
) {
    val forecast = state.forecast

    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxSize()
            .padding(16.dp)
            .statusBarsPadding()
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back",
            tint = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.clickable { onNavigateBack() }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            when {
                state.isLoading -> {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                state.error != null -> {
                    Text(
                        text = state.error,
                        color = Error,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                forecast != null -> {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = forecast.locationName,
                            style = MaterialTheme.typography.headlineLarge,
                            color = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.align(Alignment.Start)
                        )

                        Spacer(modifier = Modifier.height(64.dp))

                        Row(
                            modifier = Modifier.align(Alignment.Start)
                        ) {
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = "Calendar",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = "3-DAY FORECAST",
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        forecast.forecastDay.forEach { day ->
                            Spacer(modifier = Modifier.height(4.dp))
                            HorizontalDivider(thickness = 1.dp, color = Color.Gray)
                            Spacer(modifier = Modifier.height(4.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = day.date,
                                    color = MaterialTheme.colorScheme.onPrimary,
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
                                        color = MaterialTheme.colorScheme.onPrimary,
                                        style = MaterialTheme.typography.labelSmall
                                    )
                                }

                                Text(
                                    text = "L:${if (state.temperatureUnit == "°C") day.minTempCelsius.toInt() else day.minTempFahrenheit.toInt()}°",
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    style = MaterialTheme.typography.bodyLarge
                                )

                                Text(
                                    text = "H:${if (state.temperatureUnit == "°C") day.maxTempCelsius.toInt() else day.maxTempFahrenheit.toInt()}°",
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(32.dp))

                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.tertiary)
                                .size(60.dp)
                                .clickable { onAddForecast(forecast) },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add",
                                tint = MaterialTheme.colorScheme.onTertiary
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Add to start page",
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}
