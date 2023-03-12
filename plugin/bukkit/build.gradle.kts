import io.papermc.hangarpublishplugin.model.Platforms
import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

plugins {
    kotlin("jvm") version "1.8.0"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.3"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("xyz.jpenilla.run-paper") version "2.0.1"
    id("io.papermc.hangar-publish-plugin") version "0.0.3"
    id("com.modrinth.minotaur") version "2.+"
}

group = "dev.themeinerlp"
val baseVersion = "0.0.1"

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
if (System.getenv().containsKey("CI")) {
    val finalVersion = if (System.getenv("GITHUB_REF_NAME").equals("main")) {
        "$baseVersion-RELEASE"
    } else {
        baseVersion + "-SNAPSHOT+" + System.getenv("SHA_SHORT")
    }
    version = finalVersion
} else {
    version = baseVersion
}

hangarPublish {
    if (System.getenv().containsKey("CI")) {
        publications.register("SolarSystem") {
            val finalVersion = if (System.getenv("GITHUB_REF_NAME").equals("main")) {
                "$baseVersion-RELEASE"
            } else {
                baseVersion + "-SNAPSHOT+" + System.getenv("SHA_SHORT")
            }
            version.set(finalVersion)
            channel.set(System.getenv("HANGAR_CHANNEL"))
            changelog.set("Automated publish")
            apiKey.set(System.getenv("HANGAR_SECRET"))
            owner.set("OneLiteFeather")
            slug.set("SolarSystem")

            platforms {
                register(Platforms.PAPER) {
                    jar.set(tasks.shadowJar.flatMap { it.archiveFile })
                    platformVersions.set(listOf("1.19", "1.19.1", "1.19.2", "1.19.3"))
                }
            }
        }
    }
}
if (System.getenv().containsKey("CI")) {
    modrinth {
        token.set(System.getenv("MODRINTH_TOKEN"))
        projectId.set("O2HC6NZY")
        val finalVersion = if (System.getenv("GITHUB_REF_NAME").equals("main")) {
            "$baseVersion-RELEASE"
        } else {
            baseVersion + "-SNAPSHOT+" + System.getenv("SHA_SHORT")
        }
        versionNumber.set(finalVersion)
        versionType.set(System.getenv("MODRINTH_CHANNEL"))
        uploadFile.set(tasks.shadowJar as Any)
        gameVersions.addAll(listOf("1.19", "1.19.1", "1.19.2", "1.19.3"))
        loaders.add("paper")
        loaders.add("bukkit")
        changelog.set("Automated publish")
        dependencies { // A special DSL for creating dependencies
        }
    }
}