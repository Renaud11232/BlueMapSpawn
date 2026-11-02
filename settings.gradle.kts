rootProject.name = "bluemap-spawn"

subproject(":bluemap-spawn-common", "common")
subproject(":bluemap-spawn-bukkit", "bukkit")
subproject(":bluemap-spawn-fabric", "fabric")

fun subproject(name: String, path: String) {
    include(name)
    project(name).projectDir = file(path)
}