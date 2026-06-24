# Module: :core:data

The `:core:data` module provides the implementation for the repository interfaces defined in the `:core:domain` module. It manages data retrieval from both local database and remote API sources.

## Responsibilities

- **Local Storage**: Implements the Room database, DAOs, and entities for persistent storage.
- **Remote API**: Handles network requests using Retrofit to fetch weather data.
- **Repositories**: Implementation of domain repository interfaces, coordinating between local and remote data sources.
- **Mappers**: Logic to convert between Data Transfer Objects (DTOs), Database Entities, and Domain Models.
- **Dependency Injection**: Hilt modules for providing data-related dependencies (Network, Database).

## Package Structure

- `com.nhdtech.apps.data.local`: Room database configuration and DAOs.
- `com.nhdtech.apps.data.network`: Retrofit service interfaces and DTOs.
- `com.nhdtech.apps.data.repository`: Concrete implementations of domain repositories.
- `com.nhdtech.apps.data.mapper`: Data conversion logic.
- `com.nhdtech.apps.data.di`: Hilt modules for data layer.
