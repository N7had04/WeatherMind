# Module: :feature-home

The `:feature-home` module is responsible for the main screen of the application, which displays the current weather and forecasts for the user's location or selected cities.

## Sub-modules

### `:feature-home:ui`
- Contains the `HomeScreen` Composable.
- Implements the `HomeViewModel` and `HomeUiState`.
- Manages location permissions and triggers weather updates.

### `:feature-home:domain`
- Defines the `ForecastRepository` interface (for home-specific needs if any).
- Contains use cases like `GetWeatherForecastUseCase`.

### `:feature-home:data`
- Implements the home-specific data logic.
- Provides Hilt modules for home feature dependencies.
