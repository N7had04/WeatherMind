# Module: :core:domain

The `:core:domain` module is a pure Kotlin module that contains the core business logic and models for the WeatherMind application. It is independent of any Android-specific dependencies.

## Responsibilities

- **Models**: Defines the data structures used throughout the application (e.g., `WeatherForecast`, `Coordinates`).
- **Repositories**: Defines interfaces for data operations. These interfaces are implemented in the `:core:data` module.
- **Use Cases**: Contains domain-specific business rules and orchestrates data flow between repositories and the UI.
- **Utilities**: General-purpose helper functions and classes.

## Package Structure

- `com.nhdtech.apps.domain.model`: Core data models.
- `com.nhdtech.apps.domain.repository`: Definitions of data access interfaces.
- `com.nhdtech.apps.domain.usecase`: Business logic implementations.
- `com.nhdtech.apps.domain.util`: Common utilities.
