# Module: :feature-cities

The `:feature-cities` module handles city-specific functionality, including searching for new cities and managing the list of saved locations.

## Sub-modules

### `:feature-cities:ui`
- **Screens**:
    - `CitiesScreen`: Displays the list of saved cities with weather summaries and swipe-to-delete functionality.
    - `SearchScreen`: Provides an interface for searching cities by name.
    - `SearchResultScreen`: Shows the detailed weather forecast for a searched city before it's saved.
- **Components**: Custom UI elements like `SwipeToDeleteCard`.
- **ViewModel**: `CitiesViewModel` manages the state for all cities-related screens.

### `:feature-cities:domain`
- **Models**: Feature-specific models if any.
- **Use Cases**:
    - `SearchCitiesUseCase`: Searches for cities matching a query.
    - `GetAllCitiesForecastsFromDbUseCase`: Retrieves all saved city forecasts.
    - `DeleteForecastFromDbUseCase`: Removes a city from the saved list.
    - `UpdateForecastOrderUseCase`: Handles reordering of saved cities.

### `:feature-cities:data`
- **Repositories**: `CitiesRepositoryImpl` implements the data fetching and storage logic for cities.
- **Dependency Injection**: Hilt modules providing city-feature specific dependencies.
