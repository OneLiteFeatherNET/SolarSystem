
rootProject.name = "Solarsystem"
pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://files.minecraftforge.net/maven/")
    }

}
if (file("patched-multiverse").exists()) {
    includeBuild("patched-multiverse")
}

