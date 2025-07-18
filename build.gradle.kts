plugins {
    kotlin("jvm") version "2.1.0"
}
repositories {
    mavenCentral()
}

group = "ir.beigirad"
version = "1.0-SNAPSHOT"

dependencies {
    api("org.jline:jline:3.23.0")
}

kotlin {
    jvmToolchain(17)
}