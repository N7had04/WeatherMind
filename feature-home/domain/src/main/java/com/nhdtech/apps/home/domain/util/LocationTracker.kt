package com.nhdtech.apps.home.domain.util

import com.nhdtech.apps.domain.model.Coordinates

interface LocationTracker {
    suspend fun getCurrentLocation(): Coordinates?
}