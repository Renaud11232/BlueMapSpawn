plugins {
    id("java-library")
}

repositories {
    mavenCentral()
    maven("https://repo.bluecolored.de/releases")
    maven("https://hub.spigotmc.org/nexus/content/groups/public/")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}
