import gradle.kotlin.dsl.accessors._0cb39c16b209519d61ee18b0fceac003.java

plugins {
    id("bluemap-spawn-java")
    id("net.fabricmc.fabric-loom")
    id("com.gradleup.shadow")
}

val shadow = project.configurations.maybeCreate("shadow").apply {
    isCanBeResolved = true
    isCanBeConsumed = false
}

project.configurations.implementation {
    extendsFrom(shadow)
}

tasks {
    processResources {
        val projectVersion = project.version.toString()
        val projectDescription = project.description.toString()
        val website = project.property("website").toString()
        val javaVersion = project.property("java-version").toString()
        val minecraftApiVersion = project.property("minecraft-api-version").toString()
        filesMatching("fabric.mod.json") {
            expand(
                "version" to projectVersion,
                "description" to projectDescription,
                "website" to website,
                "javaVersion" to javaVersion,
                "apiVersion" to minecraftApiVersion,
            )
        }
    }
    shadowJar {
        configurations = listOf(shadow)
    }
    register<Jar>("mergeShadowJarAndJar") {
        dependsOn(shadowJar, jar)
        from(
            zipTree(shadowJar.map { it.outputs.files.singleFile }).matching {
                exclude("fabric.mod.json")
            },
            zipTree(jar.map { it.outputs.files.singleFile }).matching {
                include("META-INF/jars/**")
                include("fabric.mod.json")
            }
        )
        destinationDirectory = project.layout.buildDirectory.dir("release")
    }
    assemble {
        dependsOn("mergeShadowJarAndJar")
    }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}