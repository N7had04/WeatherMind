package com.nhdtech.apps.weathermind.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nhdtech.apps.home.ui.screens.HomeScreen
import com.nhdtech.apps.home.ui.HomeViewModel
import com.nhdtech.apps.home.ui.screens.SettingsScreen
import com.nhdtech.apps.ui.CitiesScreen
import com.nhdtech.apps.ui.CitiesViewModel

@Composable
fun Navigation(
    navController: NavHostController = rememberNavController(),
    homeViewModel: HomeViewModel = hiltViewModel(),
    citiesViewModel: CitiesViewModel = hiltViewModel()
) {
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

            )
        }

        composable(route = Screens.Settings.route) {
            SettingsScreen(

            )
        }
    }
}