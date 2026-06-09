package com.nhdtech.apps.home.data.util

import android.annotation.SuppressLint
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.nhdtech.apps.domain.model.Coordinates
import com.nhdtech.apps.home.domain.util.LocationTracker
import kotlinx.coroutines.tasks.await

class DefaultLocationTracker(
    private val fusedLocationClient: FusedLocationProviderClient
) : LocationTracker {

    @SuppressLint("MissingPermission")
    override suspend fun getCurrentLocation(): Coordinates? {
        return fusedLocationClient
            .getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
            .await()?.let {
                Log.d("DefaultLocationTracker", "getCurrentLocation: ${it.latitude}, ${it.longitude}")
                Coordinates(it.latitude, it.longitude)
            }
    }
}
