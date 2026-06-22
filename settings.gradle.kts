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
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        // venus-sdk published to GitHub Packages (private). Creds in ~/.gradle/gradle.properties
        // (gpr.user/gpr.key) or env GITHUB_ACTOR/GITHUB_TOKEN — token needs read:packages.
        maven {
            url = uri("https://maven.pkg.github.com/duylt-dev/Filter-Change-Hair-Color-Pro-Core")
            credentials {
                username = providers.gradleProperty("gpr.user").orNull ?: System.getenv("GITHUB_ACTOR")
                password = providers.gradleProperty("gpr.key").orNull ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
}

rootProject.name = "FilterChangeHairColorPro"
include(":app")
include(":venus-sdk")
include(":venus-benchmark")
 