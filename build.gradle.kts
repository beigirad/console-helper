plugins {
    kotlin("jvm") version "2.1.0"
    `maven-publish`
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

publishing {
    publications {
        create<MavenPublication>("jline-helper"){
            from(project.components["kotlin"])
            groupId = group.toString()
            artifactId = "jline-helper"
            version = version.toString()
            pom {
                name.set("jline-helper")
                description.set("A helper library to work with console in kotlin-script ")
                url.set("https://github.com/beigirad/jline-helper")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://github.com/beigirad/jline-helper/blob/main/LICENSE")
                    }
                }
                scm {
                    url.set("https://github.com/beigirad/jline-helper")
                }
                developers {
                    developer {
                        name.set("Farhad Beigirad")
                        url.set("https://github.com/beigirad")
                    }
                }
            }
        }
    }
}