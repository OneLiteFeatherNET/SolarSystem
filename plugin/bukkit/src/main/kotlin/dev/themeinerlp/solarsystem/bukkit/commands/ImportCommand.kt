package dev.themeinerlp.solarsystem.bukkit.commands

import cloud.commandframework.annotations.Argument
import cloud.commandframework.annotations.CommandMethod
import cloud.commandframework.annotations.CommandPermission
import cloud.commandframework.annotations.Flag
import dev.themeinerlp.solarsystem.bukkit.model.BukkitAsteroid
import dev.themeinerlp.solarsystem.bukkit.utils.COMMANDS_IMPORT
import org.bukkit.World.Environment

class ImportCommand {

    @CommandMethod("planet import <name> <env>")
    @CommandPermission(COMMANDS_IMPORT)
    fun import(
        asteroid: BukkitAsteroid, @Argument("name") name: String, @Argument("env") environment: Environment,
        @Flag("g") generator: String?, @Flag("n") useSpawnAdjust: Boolean,
    ) {
        if (!asteroid.service.isSolarPlanet(name)) {
            asteroid.service.addPlanet(name, environment, generator, !useSpawnAdjust)
        }
    }
}
