plugins {
    kotlin("jvm") version "1.4.21"
    `java-library`
    `maven-publish`
}

group = "net.hitmc.brigadierdsl"
version = "1.0.0"

repositories {
    jcenter()
    maven(url = "https://libraries.minecraft.net")
}

dependencies {
    api("com.mojang:brigadier:1.0.18")
}

configure<PublishingExtension> {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/HitMC-Network/brigadier-dsl")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
            }
        }
    }
    publications {
        register<MavenPublication>("gpr") {
            from(components["java"])
        }
    }
}