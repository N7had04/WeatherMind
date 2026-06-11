package com.nhdtech.apps.weathermind.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nhdtech.apps.home.ui.HomeScreen
import com.nhdtech.apps.home.ui.HomeViewModel
import com.nhdtech.apps.ui.SettingsScreen
import com.nhdtech.apps.ui.screens.CitiesScreen
import com.nhdtech.apps.ui.CitiesViewModel
import com.nhdtech.apps.ui.SettingsViewModel
import com.nhdtech.apps.ui.screens.SearchResultScreen
import com.nhdtech.apps.ui.screens.SearchScreen

@Composable
fun Navigation(
    navController: NavHostController = rememberNavController(),
    homeViewModel: HomeViewModel = hiltViewModel(),
    citiesViewModel: CitiesViewModel = hiltViewModel(),
    settingsViewModel: SettingsViewModel = hiltViewModel()
) {
    val citiesState by citiesViewModel.state.collectAsState()

    NavHost(
        navController = navController,
        startDestination = Screens.Home.route
    ) {
        composable(route = Screens.Home.route) {
            val state by homeViewModel.state.collectAsState()

            HomeScreen(
                state = state,
                onPermissionGranted = homeViewModel::onPermissionGranted,
                onPermissionDenied = homeViewModel::onPermissionDenied,
                onNavigateToCities = { navController.navigate(Screens.Cities.route) },
                onNavigateToSettings = { navController.navigate(Screens.Settings.route) }
            )
        }

        composable(route = Screens.Cities.route) {
            CitiesScreen(
                state = citiesState,
                onNavigateBack = { navController.popBackStack() },
                onNavigateToSearch = { navController.navigate(Screens.Search.route) },
                onDeleteForecast = { locationName -> citiesViewModel.deleteForecastFromDb(locationName) }
            )
        }

        composable(route = Screens.Settings.route) {
            val state by settingsViewModel.state.collectAsState()

            SettingsScreen(
                state = state,
                onNavigateBack = { navController.popBackStack() },
                onSetTemperatureUnit = { settingsViewModel.onSetTemperatureUnit(it) },
                onSetWindSpeedUnit = { settingsViewModel.onSetWindSpeedUnit(it) },
                onSetAtmosphericPressureUnit = { settingsViewModel.onSetAtmosphericPressureUnit(it) }
            )
        }

        composable(route = Screens.Search.route) {
            SearchScreen(
                state = citiesState,
                onNavigateBack = { navController.popBackStack() },
                onSearchCityTextChange = { citiesViewModel.onSearchCityTextChange(it) },
                onSearch = { citiesViewModel.searchCities(it) },
                onGetForecast = {
                    citiesViewModel.getForecastFromApi(it)
                    navController.navigate(Screens.SearchResult.route)
                }
            )
        }

        composable(route = Screens.SearchResult.route) {
            SearchResultScreen(

            )
        }
    }
}