plugins {
    `bluemap-spawn-bukkit-release`
}

project.description = project.property("bluemap-spawn-description").toString()

dependencies {
    implementation(project(":bluemap-spawn-common"))
    compileOnly(libs.spigot.api)
}
