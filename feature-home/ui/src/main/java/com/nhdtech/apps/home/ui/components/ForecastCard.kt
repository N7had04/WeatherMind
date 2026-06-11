package com.nhdtech.apps.home.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.nhdtech.apps.domain.model.WeatherForecast
import com.nhdtech.apps.home.ui.HomeUiState

@Composable
fun ForecastCard(
    modifier: Modifier = Modifier,
    forecast: WeatherForecast,
    state: HomeUiState
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(top = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = forecast.locationName,
            fontSize = 32.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "${if (state.temperatureUnit == "°C") forecast.temperatureCelsius else forecast.temperatureFahrenheit}°",
            fontSize = 96.sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = forecast.conditionText,
            fontSize = 24.sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "H:${if (state.temperatureUnit == "°C") forecast.forecastDay[0].maxTempCelsius else forecast.forecastDay[0].maxTempFahrenheit}° " +
                    "L:${if (state.temperatureUnit == "°C") forecast.forecastDay[0].minTempCelsius else forecast.forecastDay[0].minTempFahrenheit}°",
            fontSize = 24.sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFF343f5e))
                .padding(8.dp)
        ) {
            Text(
                text = "AQI: ${forecast.airQualityIndex}",
                fontSize = 24.sp,
                color = Color.White,
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp))
                .background(Color(0xFF343f5e))
                .padding(16.dp)
        ) {
            Row {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Calendar",
                    tint = Color.White
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "3-DAY FORECAST",
                    fontSize = 20.sp,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            forecast.forecastDay.forEach { day ->
                Spacer(modifier = Modifier.height(4.dp))
                HorizontalDivider(thickness = 1.dp, color = Color.Gray)
                Spacer(modifier = Modifier.height(4.dp))
                ForecastRow(
                    day = day,
                    state = state
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp)),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(forecast.forecastDay[0].hours) { hour ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = "${if (state.temperatureUnit == "°C") hour.temperatureCelsius else hour.temperatureFahrenheit}°",
                        fontSize = 16.sp,
                        color = Color.White
                    )

                    AsyncImage(
                        model = "https:${hour.conditionIcon}",
                        contentDescription = "Weather icon",
                        modifier = Modifier.size(48.dp)
                    )

                    Text(
                        text = "${if (state.windSpeedUnit == "km/h") hour.windSpeedKph else hour.windSpeedMph}" +
                                if (state.windSpeedUnit == "km/h") "km/h" else "mph",
                        fontSize = 12.sp,
                        color = Color.White
                    )

                    Text(
                        text = hour.time.takeLast(5),
                        fontSize = 12.sp,
                        color = Color.White
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                WeatherComponentCard(
                    modifier = Modifier.weight(1f).aspectRatio(1f),
                    icon = Icons.Default.WbSunny,
                    title = "UV INDEX",
                    value = "${forecast.uvIndex}"
                )

                WeatherComponentCard(
                    modifier = Modifier.weight(1f).aspectRatio(1f),
                    icon = Icons.Default.WaterDrop,
                    title = "HUMIDITY",
                    value = "${forecast.humidity}%"
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                WeatherComponentCard(
                    modifier = Modifier.weight(1f).aspectRatio(1f),
                    icon = Icons.Default.Thermostat,
                    title = "FEELS LIKE",
                    value = "${if (state.temperatureUnit == "°C") forecast.feelsLikeCelsius else forecast.feelsLikeFahrenheit}°"
                )

                WeatherComponentCard(
                    modifier = Modifier.weight(1f).aspectRatio(1f),
                    icon = Icons.Default.Air,
                    title = "WIND",
                    value = "${forecast.winDirection} ${if (state.windSpeedUnit == "km/h") forecast.windKph else forecast.windMph}" +
                            if (state.windSpeedUnit == "km/h") "km/h" else "mph"
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                WeatherComponentCard(
                    modifier = Modifier.weight(1f).aspectRatio(1f),
                    icon = Icons.Default.WaterDrop,
                    title = "CHANCE OF RAIN",
                    value = "${forecast.chanceOfRain}%"
                )

                WeatherComponentCard(
                    modifier = Modifier.weight(1f).aspectRatio(1f),
                    icon = Icons.Default.Speed,
                    title = "PRESSURE",
                    value = "${if (state.atmosphericPressureUnit == "mbar") forecast.pressureMb else forecast.pressureIn}" +
                            if (state.atmosphericPressureUnit == "mbar") "mbar" else "inHg"
                )
            }
        }
    }
}
