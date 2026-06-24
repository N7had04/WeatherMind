# Module: :app

The `:app` module is the entry point of the WeatherMind application. It orchestrates the various feature modules and provides the global navigation structure.

## Responsibilities

- **Application Class**: Initializing application-wide configurations (e.g., Hilt, Firebase).
- **MainActivity**: The single activity that hosts the UI.
- **Navigation**: Defining the navigation graph and routes between different features using Jetpack Compose Navigation.
- **Dependency Injection**: Global Hilt modules that provide application-level dependencies.

## Key Components

- `WeatherMindApp.kt`: The `Application` class.
- `MainActivity.kt`: The main entry point for the UI.
- `navigation/`: Contains navigation logic and screen definitions.
- `google-services.json`: Configuration for Firebase services.
