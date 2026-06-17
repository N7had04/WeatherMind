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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.nhdtech.apps.ui.CitiesUiState
import com.nhdtech.apps.ui.components.ReorderableCityList

@Composable
fun CitiesScreen(
    modifier: Modifier = Modifier,
    state: CitiesUiState,
    onNavigateBack: () -> Unit,
    onNavigateToSearch: () -> Unit,
    onDeleteForecast: (String) -> Unit,
    onReorder: (from: Int, to: Int) -> Unit
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxSize()
            .statusBarsPadding()
            .padding(16.dp)
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back",
            tint = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.clickable { onNavigateBack() }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Weather",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(MaterialTheme.colorScheme.secondary)
                .clickable { onNavigateToSearch() }
                .padding(horizontal = 14.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier.size(18.dp)
            )
            Text(
                text = "Search for a city",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSecondary
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(modifier = Modifier.fillMaxWidth().weight(1f)) {
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
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                state.savedForecasts.isNotEmpty() -> {
                    ReorderableCityList(
                        forecasts = state.savedForecasts,
                        temperatureUnit = state.temperatureUnit,
                        onDeleteForecast = onDeleteForecast,
                        onReorder = onReorder
                    )
                }
            }
        }
    }
}
