package com.nhdtech.apps.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nhdtech.apps.domain.util.ThemeMode
import com.nhdtech.apps.ui.components.SettingsDialog
import com.nhdtech.apps.ui.components.SettingsRow

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    state: SettingsUiState,
    onNavigateBack: () -> Unit,
    onSetTemperatureUnit: (String) -> Unit,
    onSetWindSpeedUnit: (String) -> Unit,
    onSetAtmosphericPressureUnit: (String) -> Unit,
    onSetThemeMode: (ThemeMode) -> Unit
) {
    var showTemperatureDialog by rememberSaveable { mutableStateOf(false) }
    var showWindSpeedDialog by rememberSaveable { mutableStateOf(false) }
    var showAtmosphericPressureDialog by rememberSaveable { mutableStateOf(false) }
    var showThemeModeDialog by rememberSaveable { mutableStateOf(false) }

    if (showTemperatureDialog) {
        SettingsDialog(
            title = "Temperature units",
            options = listOf("°C", "°F"),
            selectedOption = state.temperatureUnit,
            onOptionSelected = {
                showTemperatureDialog = false
                onSetTemperatureUnit(it)
            },
            onDismiss = { showTemperatureDialog = false }
        )
    }

    if (showWindSpeedDialog) {
        SettingsDialog(
            title = "Wind speed units",
            options = listOf("km/h", "mph"),
            selectedOption = state.windSpeedUnit,
            onOptionSelected = {
                showWindSpeedDialog = false
                onSetWindSpeedUnit(it)
            },
            onDismiss = { showWindSpeedDialog = false }
        )
    }

    if (showAtmosphericPressureDialog) {
        SettingsDialog(
            title = "Pressure units",
            options = listOf("mbar", "inHg"),
            selectedOption = state.atmosphericPressureUnit,
            onOptionSelected = {
                showAtmosphericPressureDialog = false
                onSetAtmosphericPressureUnit(it)
            },
            onDismiss = { showAtmosphericPressureDialog = false }
        )
    }

    if (showThemeModeDialog) {
        SettingsDialog(
            title = "Theme",
            options = listOf("System", "Light", "Dark"),
            selectedOption = when (state.themeMode) {
                ThemeMode.LIGHT -> "Light"
                ThemeMode.DARK -> "Dark"
                else -> "System"
            },
            onOptionSelected = {
                val mode = when (it) {
                    "Light" -> ThemeMode.LIGHT
                    "Dark" -> ThemeMode.DARK
                    else -> ThemeMode.SYSTEM
                }
                onSetThemeMode(mode)
                showThemeModeDialog = false
            },
            onDismiss = { showThemeModeDialog = false }
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        IconButton(
            onClick = onNavigateBack,
            modifier = Modifier.size(40.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Settings",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Appearance",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f)
        )

        Spacer(modifier = Modifier.height(8.dp))

        SettingsRow(
            description = "Theme",
            unit = when (state.themeMode) {
                ThemeMode.LIGHT -> "Light"
                ThemeMode.DARK -> "Dark"
                else -> "System"
            },
            onClickRow = { showThemeModeDialog = true }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Units",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f)
        )

        Spacer(modifier = Modifier.height(8.dp))

        SettingsRow(
            description = "Temperature units",
            unit = state.temperatureUnit,
            onClickRow = { showTemperatureDialog = true }
        )

        Spacer(modifier = Modifier.height(12.dp))

        SettingsRow(
            description = "Wind speed units",
            unit = state.windSpeedUnit,
            onClickRow = { showWindSpeedDialog = true }
        )

        Spacer(modifier = Modifier.height(12.dp))

        SettingsRow(
            description = "Pressure units",
            unit = state.atmosphericPressureUnit,
            onClickRow = { showAtmosphericPressureDialog = true }
        )
    }
}
