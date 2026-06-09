package com.nhdtech.apps.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nhdtech.apps.domain.model.WeatherForecast
import com.nhdtech.apps.domain.usecase.DeleteForecastFromDbUseCase
import com.nhdtech.apps.domain.usecase.GetForecastFromApiUseCase
import com.nhdtech.apps.domain.usecase.SaveForecastToDbUseCase
import com.nhdtech.apps.domain.util.Resource
import com.nhdtech.apps.home.domain.usecase.GetAllForecastsFromDbUseCase
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
    private val getAllForecastsFromDbUseCase: GetAllForecastsFromDbUseCase,
    private val deleteForecastFromDbUseCase: DeleteForecastFromDbUseCase,
    private val getForecastFromApiUseCase: GetForecastFromApiUseCase,
    private val saveForecastToDbUseCase: SaveForecastToDbUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(CitiesUiState())
    val state = _state.asStateFlow()

    init {
        getAllForecastsFromDbUseCase()
            .onEach { forecasts ->
                _state.update { it.copy(savedForecasts = forecasts, isLoading = false) }
            }
            .launchIn(viewModelScope)
    }

    fun deleteForecastFromDb(forecast: WeatherForecast) {
        viewModelScope.launch {
            deleteForecastFromDbUseCase(forecast)
        }
    }

    fun saveForecastToDb(forecast: WeatherForecast) {
        viewModelScope.launch {
            saveForecastToDbUseCase(forecast)
        }
    }

    fun getForecastFromApi(location: String) {
        viewModelScope.launch {
            try {
                _state.update {
                    it.copy(
                        isLoading = true,
                        error = null
                    )
                }

                when (val result = getForecastFromApiUseCase(location)) {
                    is Resource.Success<WeatherForecast> -> {
                        _state.update {
                            it.copy(
                                forecast = result.data,
                                isLoading = false
                            )
                        }
                    }
                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                error = result.message,
                                isLoading = false
                            )
                        }
                    }
                    is Resource.Loading -> Unit
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        error = e.message ?: "Unknown error",
                        isLoading = false
                    )
                }
            }
        }
    }
}