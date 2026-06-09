package com.nhdtech.apps.home.domain.usecase

import com.nhdtech.apps.domain.model.Coordinates
import com.nhdtech.apps.home.domain.util.LocationTracker

class GetCurrentLocationUseCase(private val locationTracker: LocationTracker) {
    suspend operator fun invoke() = locationTracker.getCurrentLocation()
}