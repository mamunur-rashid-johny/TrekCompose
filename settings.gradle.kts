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

rootProject.name = "Trek"
include(":app")
include(":core")
include(":feature:auth:ui")
include(":feature:auth:data")
include(":feature:auth:domain")
include(":feature:home:ui")
include(":feature:home:data")
include(":feature:home:domain")
