plugins {
    kotlin("jvm") version "1.8.20"
    `maven-publish`
    id("com.github.beigirad.metalava-extended") version "1.4"
}

group = "com.github.beigirad"
version = "0.6"

dependencies {
    api("org.jline:jline:3.25.0")
    implementation("eu.jrie.jetbrains:kotlin-shell-core:0.2.1")

    testImplementation(platform("org.junit:junit-bom:5.14.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

tasks.clean {
    delete("build")
}

kotlin {
    jvmToolchain(8)
}

publishing {
    publications {
        create<MavenPublication>("ConsoleHelper") {
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

            repositories {
                maven {
                    name = "PegahPublic"
                    url = uri("https://reg.production.tpsl.ir/artifactory/maven-pegah-public")
                }
            }
        }
    }
}