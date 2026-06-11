package com.nhdtech.apps.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nhdtech.apps.ui.CitiesUiState

@Composable
fun CitiesScreen(
    modifier: Modifier = Modifier,
    state: CitiesUiState,
    onNavigateBack: () -> Unit,
    onNavigateToSearch: () -> Unit,
    onDeleteForecast: (String) -> Unit
) {
    Column(
        modifier = modifier
            .background(Color.Black)
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back",
            tint = Color.White,
            modifier = Modifier.clickable { onNavigateBack() }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Weather",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.secondary)
                .fillMaxWidth()
                .clickable { onNavigateToSearch() }
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = MaterialTheme.colorScheme.onSecondary
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Search for a city",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSecondary
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        when {
            state.isLoading -> {
                Spacer(modifier = Modifier.height(200.dp))
                CircularProgressIndicator(
                    color = Color(0xFF0627C7),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            state.error != null -> {
                Spacer(modifier = Modifier.height(200.dp))
                Text(
                    text = state.error,
                    color = Color.White,
                    fontSize = 20.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            state.savedForecasts.isNotEmpty() -> {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    itemsIndexed(state.savedForecasts) { index, forecast ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(Color(0xFF2C334D))
                                    .weight(0.85f)
                                    .fillMaxHeight()
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxHeight(),
                                    verticalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Column{
                                        Text(
                                            text = if (index == 0) "My Location" else forecast.locationName,
                                            fontSize = 24.sp,
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold
                                        )

                                        Spacer(modifier = Modifier.height(4.dp))

                                        Text(
                                            text = if (index == 0) forecast.locationName else forecast.lastUpdated.takeLast(5),
                                            fontSize = 16.sp,
                                            color = Color.White
                                        )
                                    }

                                    Text(
                                        text = forecast.conditionText,
                                        fontSize = 16.sp,
                                        color = Color.White
                                    )
                                }

                                Column(
                                    modifier = Modifier.fillMaxHeight(),
                                    verticalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "${if (state.temperatureUnit == "°C") forecast.temperatureCelsius else forecast.temperatureFahrenheit}°",
                                        fontSize = 48.sp,
                                        color = Color.White
                                    )

                                    Text(
                                        text = "H:${if (state.temperatureUnit == "°C") forecast.maxTempCelsius else forecast.maxTempFahrenheit}° " +
                                                "L:${if (state.temperatureUnit == "°C") forecast.minTempCelsius else forecast.minTempFahrenheit}°",
                                        fontSize = 16.sp,
                                        color = Color.White
                                    )
                                }
                            }

                            if (index != 0) {
                                Spacer(modifier = Modifier.width(8.dp))

                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete",
                                    tint = Color.Red,
                                    modifier = Modifier
                                        .weight(0.15f)
                                        .size(32.dp)
                                        .clickable { onDeleteForecast(forecast.locationName) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}