# Module: :feature-settings

The `:feature-settings` module allows users to customize their experience within the WeatherMind app, including units of measurement and theme preferences.

## Sub-modules

### `:feature-settings:ui`
- **Screens**:
    - `SettingsScreen`: Provides options to change temperature units (Celsius/Fahrenheit), wind speed units, pressure units, and the app's theme mode.
- **ViewModel**: `SettingsViewModel` manages the user's preferences state and interacts with use cases to update settings.

### `:feature-settings:domain`
- **Repositories**: `SettingsRepository` interface for managing user preferences.
- **Use Cases**:
    - `SetTemperatureUnitUseCase`
    - `SetWindSpeedUnitUseCase`
    - `SetAtmosphericPressureUnitUseCase`
    - `SetThemeModeUseCase`

### `:feature-settings:data`
- **Repositories**: Implementation of `SettingsRepository`, likely using DataStore or SharedPreferences for persistent storage of user settings.
- **Dependency Injection**: Hilt modules for providing settings-related dependencies.
