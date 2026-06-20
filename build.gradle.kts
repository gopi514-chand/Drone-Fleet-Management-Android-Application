plugins {
    id("com.android.application") version "8.2.0" apply false
    kotlin("android") version "1.9.0" apply false
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
