# WeatherMind

WeatherMind is a modern Android weather application built with Jetpack Compose, Hilt, Room, and Retrofit, following Clean Architecture principles and a multi-module project structure.

## Features

- **Current Weather**: View real-time weather information for your current location.
- **Location Search**: Search for cities worldwide to get weather forecasts.
- **Saved Locations**: Manage a list of saved cities and quickly switch between them.
- **Detailed Forecasts**: Hourly and daily weather forecasts.
- **Settings**: Customize units for temperature, wind speed, atmospheric pressure, and app theme.

## Architecture

The project follows Clean Architecture and is divided into several modules to ensure separation of concerns and maintainability.

### Module Structure

- **`:app`**: The main application module, containing the `MainActivity`, global navigation, and application-level configuration.
- **`:core`**: Contains shared components used across different features.
    - **`:core:domain`**: Pure Kotlin module with business logic, models, and repository interfaces.
    - **`:core:data`**: Implementation of repository interfaces, handling data from local (Room) and remote (Retrofit) sources.
    - **`:core:ui`**: Shared UI components, theme, and resources.
- **`:feature-*`**: Feature-specific modules, each potentially containing `ui`, `domain`, and `data` sub-modules.
    - **`:feature-home`**: Displays current weather and main forecast.
    - **`:feature-cities`**: City management and search functionality.
    - **`:feature-settings`**: Application configuration and user preferences.

## Tech Stack

- **UI**: Jetpack Compose
- **Dependency Injection**: Hilt
- **Networking**: Retrofit & OkHttp
- **Local Database**: Room
- **Async & Reactive**: Kotlin Coroutines & Flow
- **Navigation**: Jetpack Navigation Compose
- **Architecture**: MVVM with Clean Architecture

## Getting Started

1. Clone the repository.
2. Open the project in Android Studio.
3. Build and run the app on an emulator or physical device.

---
*Created by NHDTech Apps*
