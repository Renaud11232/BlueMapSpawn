plugins {
    `bluemap-spawn-fabric-release`
}

project.description = project.property("bluemap-spawn-description").toString()

dependencies {
    shadow(project(":bluemap-spawn-common"))
    minecraft(libs.minecraft)
    compileOnlyApi(libs.fabric.loader)
    compileOnlyApi(libs.fabric.api)
}
