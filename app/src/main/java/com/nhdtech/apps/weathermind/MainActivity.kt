package com.nhdtech.apps.weathermind

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.nhdtech.apps.ui.SettingsViewModel
import com.nhdtech.apps.ui.theme.WeatherMindTheme
import com.nhdtech.apps.weathermind.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: SettingsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val state by viewModel.state.collectAsState()

            WeatherMindTheme(themeMode = state.themeMode) {
                Navigation()
            }
        }
    }
}