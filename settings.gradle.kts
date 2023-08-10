pluginManagement {
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
    }
}

rootProject.name = "MagisterAPI"
include(":magisterapi")
includeBuild("convention-plugins")