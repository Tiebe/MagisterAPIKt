plugins {
    kotlin("jvm")
}

group = "dev.tiebe"
version = "1.0"

repositories {
    mavenCentral()
}
val ktorVersion = "2.3.13"

dependencies {
    implementation(project(":magisterapi"))
    implementation("io.github.evanrupert:excelkt:1.0.2")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("io.ktor:ktor-client-java:$ktorVersion")
}