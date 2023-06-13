
rootProject.name = "Solarsystem"
pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://files.minecraftforge.net/maven/")
    }

}
if (file("Multiverse-Core-Patched").exists()) {
    includeBuild("Multiverse-Core-Patched")
}

