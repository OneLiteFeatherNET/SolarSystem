import net.minecrell.pluginyml.bukkit.BukkitPluginDescription
plugins {
    kotlin("jvm") version "1.8.0"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.2"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("xyz.jpenilla.run-paper") version "2.0.1"
}

group = "dev.themeinerlp"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.19.3-R0.1-SNAPSHOT")
    api(project(":plugin:api"))
    implementation("cloud.commandframework:cloud-paper:1.8.0")
    implementation("cloud.commandframework:cloud-annotations:1.8.0")
    implementation("cloud.commandframework:cloud-minecraft-extras:1.8.0")
    implementation("net.kyori:adventure-text-minimessage:4.12.0")
    implementation("me.lucko:commodore:2.2") {
        isTransitive = false
    }

    bukkitLibrary("com.h2database:h2:2.1.214")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks {
    runServer {
        minecraftVersion("1.19.3")
    }
}

bukkit {
    name = "SolarSystem"
    load = BukkitPluginDescription.PluginLoadOrder.POSTWORLD
    main = "dev.themeinerlp.solarsystem.bukkit.BukkitSolar"
    apiVersion = "1.13"
    authors = listOf("TheMeinerLP")

    defaultPermission = net.minecrell.pluginyml.bukkit.BukkitPluginDescription.Permission.Default.OP
}