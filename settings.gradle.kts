pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "MagisterAPI"
rootProject.children.forEach {
    it.name = when(it.name) {
        "shared-jvm" -> "magisterapi-jvm"
        else -> "magisterapi"
    }
}
include(":shared")