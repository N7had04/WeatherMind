pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "WeatherMind"
include(":app")
include(":core:data")
include(":core:domain")
include(":core:ui")
include(":feature-home:ui")
include(":feature-home:domain")
include(":feature-home:data")
include(":feature-cities:data")
include(":feature-cities:ui")
include(":feature-cities:domain")
include(":feature-settings:ui")
include(":feature-settings:domain")
include(":feature-settings:data")
