package dev.themeinerlp.solarsystem.bukkit.commands

import cloud.commandframework.annotations.Argument
import cloud.commandframework.annotations.CommandMethod
import cloud.commandframework.annotations.CommandPermission
import dev.themeinerlp.solarsystem.bukkit.model.BukkitAsteroid
import dev.themeinerlp.solarsystem.bukkit.utils.COMMANDS_LOAD

class LoadCommand {

    @CommandMethod("planet load <name>")
    @CommandPermission(COMMANDS_LOAD)
    fun load(asteroid: BukkitAsteroid, @Argument("name") name: String) {
        asteroid.service.loadPlanetByName(name)
    }
}
