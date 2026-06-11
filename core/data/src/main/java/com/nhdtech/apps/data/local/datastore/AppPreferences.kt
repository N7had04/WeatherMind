package com.nhdtech.apps.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppPreferences(private val dataStore: DataStore<Preferences>) {
    companion object {
        private val TEMPERATURE_UNIT = stringPreferencesKey("temperature_unit")
        private val WIND_SPEED_UNIT = stringPreferencesKey("wind_speed_unit")
        private val ATMOSPHERIC_PRESSURE_UNIT = stringPreferencesKey("atmospheric_pressure_unit")
    }

    val temperatureUnit: Flow<String> = dataStore.data.map {
        it[TEMPERATURE_UNIT] ?: "°C"
    }
    val windSpeedUnit: Flow<String> = dataStore.data.map {
        it[WIND_SPEED_UNIT] ?: "km/h"
    }
    val atmosphericPressureUnit: Flow<String> = dataStore.data.map {
        it[ATMOSPHERIC_PRESSURE_UNIT] ?: "mbar"
    }

    suspend fun setTemperatureUnit(value: String) {
        dataStore.edit {
            it[TEMPERATURE_UNIT] = value
        }
    }

    suspend fun setWindSpeedUnit(value: String) {
        dataStore.edit {
            it[WIND_SPEED_UNIT] = value
        }
    }

    suspend fun setAtmosphericPressureUnit(value: String) {
        dataStore.edit {
            it[ATMOSPHERIC_PRESSURE_UNIT] = value
        }
    }
}