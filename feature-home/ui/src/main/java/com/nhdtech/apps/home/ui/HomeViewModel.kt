package com.nhdtech.apps.home.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nhdtech.apps.domain.model.WeatherForecast
import com.nhdtech.apps.domain.usecase.GetForecastFromApiUseCase
import com.nhdtech.apps.domain.usecase.SaveForecastToDbUseCase
import com.nhdtech.apps.domain.util.Resource
import com.nhdtech.apps.home.domain.usecase.GetAllForecastsFromDbUseCase
import com.nhdtech.apps.home.domain.usecase.GetCurrentLocationUseCase
import com.nhdtech.apps.home.domain.usecase.GetHasAccessedLocationUseCase
import com.nhdtech.apps.home.domain.usecase.GetLocationForecastUseCase
import com.nhdtech.apps.home.domain.usecase.SetHasAccessedLocationUseCase
import com.nhdtech.apps.home.domain.util.AppConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getAllForecastsFromDbUseCase: GetAllForecastsFromDbUseCase,
    private val getLocationForecastUseCase: GetLocationForecastUseCase,
    private val saveForecastToDbUseCase: SaveForecastToDbUseCase,
    private val getForecastFromApiUseCase: GetForecastFromApiUseCase,
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase,
    getHasAccessedLocationUseCase: GetHasAccessedLocationUseCase,
    private val setHasAccessedLocationUseCase: SetHasAccessedLocationUseCase
): ViewModel() {
    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val hasAccessed = getHasAccessedLocationUseCase().first()
            _state.update { it.copy(hasAccessedLocationBefore = hasAccessed) }

            getHasAccessedLocationUseCase().drop(1)
                .onEach { _state.update { s -> s.copy(hasAccessedLocationBefore = it) } }
                .launchIn(this)

            getAllForecastsFromDbUseCase()
                .onEach { forecasts ->
                    _state.update { it.copy(savedForecasts = forecasts) }
                }
                .launchIn(this)

            val initialForecasts = getAllForecastsFromDbUseCase().first()
            Log.d("HomeViewModel", "Initial forecasts: $initialForecasts")
            updateForecast(initialForecasts)
        }
    }

    fun onPermissionGranted() {
        viewModelScope.launch {
            if (_state.value.hasAccessedLocationBefore) return@launch

            _state.update { it.copy(isLoading = true, error = null) }

            val coordinates = getCurrentLocationUseCase()

            if (coordinates == null) {
                _state.update { it.copy(isLoading = false, error = "Could not get location") }
                return@launch
            }

            when (val result = getLocationForecastUseCase(coordinates.lat, coordinates.lon)) {
                is Resource.Success -> {
                    saveForecastToDbUseCase(result.data)
                    setHasAccessedLocationUseCase(true)
                    _state.update {
                        it.copy(locationForecast = result.data, isLoading = false)
                    }
                    Log.d("HomeViewModel", "Forecast: ${result.data}")
                }
                is Resource.Error -> {
                    _state.update { it.copy(error = result.message, isLoading = false) }
                    Log.d("HomeViewModel", "Error: ${result.message}")
                }
                is Resource.Loading -> Unit
            }
        }
    }

    fun onPermissionDenied() {
        _state.update { it.copy(isLoading = false, error = "Location permission denied") }
        Log.d("HomeViewModel", "Permission denied")
    }

    private fun updateForecast(forecasts: List<WeatherForecast>) {
        if (forecasts.isEmpty()) return

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            try {
                forecasts.forEach { forecast ->
                    val timeDifference = System.currentTimeMillis() - forecast.lastUpdatedEpoch
                    if (timeDifference < AppConstants.ONE_HOUR_IN_MILLIS) return@forEach

                    when (val result = getForecastFromApiUseCase(forecast.locationName)) {
                        is Resource.Success -> {
                            Log.d("HomeViewModel", "Updating forecast for: '${forecast.locationName}'")
                            saveForecastToDbUseCase(result.data)
                        }
                        is Resource.Error ->  Log.e("HomeViewModel", "Update failed for ${forecast.locationName}: ${result.message}")
                        else -> Unit
                    }
                }
            } catch (e: Exception) {
                _state.update { it.copy(error = e.message) }
            } finally {
                _state.update { it.copy(isLoading = false) }
            }
        }
    }
}