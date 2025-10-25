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
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
rootProject.name = "Rick_Morty"
include(":app")
include(":core")
include(":core:design")
include(":core:data")
include(":core:domain")
include(":core:model")
include(":core:common")
include(":core:database")
include(":core:network")
include(":core:preferences")
include(":feature")
include(":feature:home")
include(":feature:detail-character")
include(":feature:view-episodes")
