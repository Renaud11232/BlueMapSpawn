plugins {
    alias(libs.plugins.git.version)
}

@Suppress("UNCHECKED_CAST")
val gitVersion = extra["gitVersion"] as groovy.lang.Closure<String>

allprojects {
    version = gitVersion()
}