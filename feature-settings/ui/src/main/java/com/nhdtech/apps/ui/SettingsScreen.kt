package com.nhdtech.apps.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nhdtech.apps.ui.components.SettingsDialog
import com.nhdtech.apps.ui.components.SettingsRow

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    state: SettingsUiState,
    onNavigateBack: () -> Unit,
    onSetTemperatureUnit: (String) -> Unit,
    onSetWindSpeedUnit: (String) -> Unit,
    onSetAtmosphericPressureUnit: (String) -> Unit
) {
    var showTemperatureDialog by rememberSaveable { mutableStateOf(false) }
    var showWindSpeedDialog by rememberSaveable { mutableStateOf(false) }
    var showAtmosphericPressureDialog by rememberSaveable { mutableStateOf(false) }

    if (showTemperatureDialog) {
        SettingsDialog(
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
            options = listOf("mbar", "inHg"),
            selectedOption = state.atmosphericPressureUnit,
            onOptionSelected = {
                showAtmosphericPressureDialog = false
                onSetAtmosphericPressureUnit(it)
            },
            onDismiss = { showAtmosphericPressureDialog = false }
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier.clickable { onNavigateBack() }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Settings",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Units",
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        SettingsRow(
            description = "Temperature units",
            unit = state.temperatureUnit,
            onClickRow = { showTemperatureDialog = true }
        )

        Spacer(modifier = Modifier.height(16.dp))

        SettingsRow(
            description = "Wind speed units",
            unit = state.windSpeedUnit,
            onClickRow = { showWindSpeedDialog = true }
        )

        Spacer(modifier = Modifier.height(16.dp))

        SettingsRow(
            description = "Atmospheric pressure units",
            unit = state.atmosphericPressureUnit,
            onClickRow = { showAtmosphericPressureDialog = true }
        )
    }
}