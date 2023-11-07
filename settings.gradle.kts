pluginManagement {
    includeBuild("convention-plugins")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "MagisterAPI"
include(":magisterapi")
includeBuild("convention-plugins")
include("magisterscripts")
