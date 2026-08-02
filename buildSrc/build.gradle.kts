plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    gradlePluginPortal()
    maven("https://maven.fabricmc.net/")
}

dependencies {
    fun plugin(dependency: Provider<PluginDependency>) = dependency.map {
        "${it.pluginId}:${it.pluginId}.gradle.plugin:${it.version}"
    }
    implementation(plugin(libs.plugins.shadow))
    implementation(plugin(libs.plugins.fabric.loom))
}