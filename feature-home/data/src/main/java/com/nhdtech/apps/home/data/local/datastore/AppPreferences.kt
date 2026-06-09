package com.nhdtech.apps.home.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppPreferences(private val dataStore: DataStore<Preferences>) {
    companion object {
        private val HAS_ACCESSED_LOCATION = booleanPreferencesKey("has_accessed_location")
    }

    val hasAccessedLocation: Flow<Boolean> = dataStore.data.map {
        it[HAS_ACCESSED_LOCATION] ?: false
    }

    suspend fun setHasAccessedLocation(value: Boolean) {
        dataStore.edit {
            it[HAS_ACCESSED_LOCATION] = value
        }
    }
}
