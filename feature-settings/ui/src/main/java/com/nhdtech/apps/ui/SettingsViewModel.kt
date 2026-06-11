package com.nhdtech.apps.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nhdtech.apps.domain.usecase.GetAtmosphericPressureUnitUseCase
import com.nhdtech.apps.domain.usecase.GetTemperatureUnitUseCase
import com.nhdtech.apps.domain.usecase.GetWindSpeedUnitUseCase
import com.nhdtech.apps.domain.usecase.SetAtmosphericPressureUnitUseCase
import com.nhdtech.apps.domain.usecase.SetTemperatureUnitUseCase
import com.nhdtech.apps.domain.usecase.SetWindSpeedUnitUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    getTemperatureUnitUseCase: GetTemperatureUnitUseCase,
    private val setTemperatureUnitUseCase: SetTemperatureUnitUseCase,
    getWindSpeedUnitUseCase: GetWindSpeedUnitUseCase,
    private val setWindSpeedUnitUseCase: SetWindSpeedUnitUseCase,
    getAtmosphericPressureUnitUseCase: GetAtmosphericPressureUnitUseCase,
    private val setAtmosphericPressureUnitUseCase: SetAtmosphericPressureUnitUseCase
): ViewModel() {
    private val _state = MutableStateFlow(SettingsUiState())
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
    }

    fun onSetTemperatureUnit(unit: String) {
        viewModelScope.launch {
            setTemperatureUnitUseCase(unit)
        }
    }

    fun onSetWindSpeedUnit(unit: String) {
        viewModelScope.launch {
            setWindSpeedUnitUseCase(unit)
        }
    }

    fun onSetAtmosphericPressureUnit(unit: String) {
        viewModelScope.launch {
            setAtmosphericPressureUnitUseCase(unit)
        }
    }
}