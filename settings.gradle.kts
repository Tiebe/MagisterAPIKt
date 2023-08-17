pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "MagisterAPI"
include(":magisterapi")
includeBuild("convention-plugins")
include("magisterscripts")
