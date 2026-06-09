package com.nhdtech.apps.weathermind.navigation

sealed class Screens(val route: String) {
    object Home : Screens("home_screen")
    object Cities : Screens("cities_screen")
    object Settings : Screens("settings_screen")
}