package dev.themeinerlp.solarsystem.bukkit.commands

import cloud.commandframework.annotations.Argument
import cloud.commandframework.annotations.CommandMethod
import cloud.commandframework.annotations.Flag
import dev.themeinerlp.solarsystem.bukkit.model.BukkitAsteroid
import org.bukkit.World.Environment

class ImportCommand {

    @CommandMethod("planet import <name> <env>")
    fun import(
        asteroid: BukkitAsteroid, @Argument("name") name: String, @Argument("env") environment: Environment,
        @Flag("g") generator: String?, @Flag("n") useSpawnAdjust: Boolean,
    ) {
        if (!asteroid.service.isSolarPlanet(name)) {
            asteroid.service.addPlanet(name, environment, generator, !useSpawnAdjust)
        }
    }
}
