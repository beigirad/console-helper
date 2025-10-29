plugins {
    kotlin("jvm") version "1.8.20"
    `maven-publish`
}

repositories {
    mavenCentral()
}

group = "ir.beigirad"
version = "1.0-SNAPSHOT"

dependencies {
    api("org.jline:jline:3.25.0")

    testImplementation(platform("org.junit:junit-bom:5.14.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}

publishing {
    publications {
        create<MavenPublication>("console-helper"){
            from(project.components["kotlin"])
            groupId = group.toString()
            artifactId = "console-helper"
            version = version.toString()
            pom {
                name.set("console-helper")
                description.set("A helper library to work with console in kotlin-script ")
                url.set("https://github.com/beigirad/console-helper")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://github.com/beigirad/console-helper/blob/main/LICENSE")
                    }
                }
                scm {
                    url.set("https://github.com/beigirad/console-helper")
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