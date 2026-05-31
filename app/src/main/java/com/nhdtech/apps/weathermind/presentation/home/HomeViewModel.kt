package com.nhdtech.apps.weathermind.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nhdtech.apps.weathermind.domain.usecase.GetAllForecastsFromDbUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllForecastsFromDbUseCase: GetAllForecastsFromDbUseCase
): ViewModel() {
    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    init {
        getForecasts()
    }

    fun getForecasts() {
        viewModelScope.launch {
            val forecasts = getAllForecastsFromDbUseCase.execute()
            Log.d("HomeViewModel", "Forecasts: $forecasts")
            _state.update { it.copy(forecasts = forecasts) }
        }
    }
}