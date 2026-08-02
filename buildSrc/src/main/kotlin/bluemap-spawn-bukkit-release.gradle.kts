plugins {
    id("bluemap-spawn-java")
    id("com.gradleup.shadow")
}

tasks {
    processResources {
        val projectVersion = project.version.toString()
        val bukkitApiVersion = project.property("bukkit-api-version").toString()
        val projectDescription = project.description.toString()
        val website = project.property("website").toString()
        filesMatching("plugin.yml") {
            expand(
                "version" to projectVersion,
                "apiVersion" to bukkitApiVersion,
                "description" to projectDescription,
                "website" to website
            )
        }
    }
    shadowJar {
        archiveClassifier = ""
        destinationDirectory = project.layout.buildDirectory.dir("release")
    }
    assemble {
        dependsOn(shadowJar)
    }
}