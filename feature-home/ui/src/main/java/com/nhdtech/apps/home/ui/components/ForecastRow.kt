package com.nhdtech.apps.home.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.nhdtech.apps.domain.model.WeatherForecastDay

@Composable
fun ForecastRow(
    modifier: Modifier = Modifier,
    day: WeatherForecastDay
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = day.date,
            color = Color.White,
            fontSize = 16.sp
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
                text = "${day.maxWindKph}km/h",
                color = Color.White,
                fontSize = 12.sp
            )
        }

        Text(
            text = "L:${day.minTempCelsius}°",
            color = Color.White,
            fontSize = 16.sp
        )

        Text(
            text = "H:${day.maxTempCelsius}°",
            color = Color.White,
            fontSize = 16.sp
        )
    }
}