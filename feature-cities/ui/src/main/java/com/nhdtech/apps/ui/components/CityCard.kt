package com.nhdtech.apps.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DragHandle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.nhdtech.apps.domain.model.CitiesForecast

@Composable
fun CityCard(
    forecast: CitiesForecast,
    isFirstItem: Boolean,
    temperatureUnit: String,
    isDragging: Boolean = false,
    dragModifier: Modifier = Modifier,
    modifier: Modifier = Modifier
) {
    val temp = if (temperatureUnit == "°C") forecast.temperatureCelsius else forecast.temperatureFahrenheit
    val maxTemp = if (temperatureUnit == "°C") forecast.maxTempCelsius else forecast.maxTempFahrenheit
    val minTemp = if (temperatureUnit == "°C") forecast.minTempCelsius else forecast.minTempFahrenheit

    val bgColor by animateColorAsState(
        targetValue = if (isDragging)
            MaterialTheme.colorScheme.secondary
        else
            MaterialTheme.colorScheme.tertiary,
        label = "card bg"
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(bgColor)
            .padding(horizontal = 16.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = if (isFirstItem) "My Location" else forecast.locationName,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onTertiary
                )
                Text(
                    text = if (isFirstItem) forecast.locationName
                    else forecast.lastUpdated.takeLast(5),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.6f)
                )
            }
            Text(
                text = forecast.conditionText,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.6f)
            )
        }

        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxHeight()
        ) {
            Text(
                text = "${temp.toInt()}°",
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.onTertiary
            )
            Text(
                text = "H:${maxTemp.toInt()}° L:${minTemp.toInt()}°",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.6f)
            )
        }

        if (!isFirstItem) {
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                imageVector = Icons.Default.DragHandle,
                contentDescription = "Drag to reorder",
                tint = MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.6f),
                modifier = Modifier
                    .size(20.dp)
                    .then(dragModifier)
            )
        }
    }
}
