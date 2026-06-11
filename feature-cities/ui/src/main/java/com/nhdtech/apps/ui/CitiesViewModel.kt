package com.nhdtech.apps.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nhdtech.apps.domain.model.WeatherForecast
import com.nhdtech.apps.domain.usecase.DeleteForecastFromDbUseCase
import com.nhdtech.apps.domain.usecase.GetAllCitiesForecastsFromDbUseCase
import com.nhdtech.apps.domain.usecase.GetAtmosphericPressureUnitUseCase
import com.nhdtech.apps.domain.usecase.GetForecastFromApiUseCase
import com.nhdtech.apps.domain.usecase.GetTemperatureUnitUseCase
import com.nhdtech.apps.domain.usecase.GetWindSpeedUnitUseCase
import com.nhdtech.apps.domain.usecase.SaveForecastToDbUseCase
import com.nhdtech.apps.domain.usecase.SearchCitiesUseCase
import com.nhdtech.apps.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(
    getAllCitiesForecastsFromDbUseCase: GetAllCitiesForecastsFromDbUseCase,
    private val deleteForecastFromDbUseCase: DeleteForecastFromDbUseCase,
    private val getForecastFromApiUseCase: GetForecastFromApiUseCase,
    private val saveForecastToDbUseCase: SaveForecastToDbUseCase,
    private val searchCitiesUseCase: SearchCitiesUseCase,
    getTemperatureUnitUseCase: GetTemperatureUnitUseCase,
    getWindSpeedUnitUseCase: GetWindSpeedUnitUseCase,
    getAtmosphericPressureUnitUseCase: GetAtmosphericPressureUnitUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(CitiesUiState())
    val state = _state.asStateFlow()

    init {
        getTemperatureUnitUseCase()
            .onEach { _state.update { s -> s.copy(temperatureUnit = it) } }
            .launchIn(viewModelScope)
        getWindSpeedUnitUseCase()
            .onEach { _state.update { s -> s.copy(windSpeedUnit = it) } }
            .launchIn(viewModelScope)
        getAtmosphericPressureUnitUseCase()
            .onEach { _state.update { s -> s.copy(atmosphericPressureUnit = it) } }
            .launchIn(viewModelScope)
        getAllCitiesForecastsFromDbUseCase()
            .onEach { forecasts -> _state.update { it.copy(savedForecasts = forecasts, isLoading = false) } }
            .launchIn(viewModelScope)
    }

    fun deleteForecastFromDb(locationName: String) {
        viewModelScope.launch {
            deleteForecastFromDbUseCase(locationName)
        }
    }

    fun saveForecastToDb(forecast: WeatherForecast) {
        viewModelScope.launch {
            saveForecastToDbUseCase(forecast)
        }
    }

    fun getForecastFromApi(location: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null)}
            when (val result = getForecastFromApiUseCase(location)) {
                is Resource.Success<WeatherForecast> ->  _state.update { it.copy(forecast = result.data, isLoading = false) }
                is Resource.Error -> _state.update { it.copy(error = result.message, isLoading = false) }
                is Resource.Loading -> Unit
            }
        }
    }

    fun searchCities(searchCityText: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            when (val result = searchCitiesUseCase(searchCityText)) {
                is Resource.Success -> _state.update { it.copy(cities = result.data, isLoading = false) }
                is Resource.Error -> _state.update { it.copy(error = result.message, isLoading = false) }
                is Resource.Loading -> Unit
            }
        }
    }

    fun onSearchCityTextChange(searchCityText: String) {
        _state.update { it.copy(searchCityText = searchCityText) }
    }
}