package dev.themeinerlp.solarsystem.bukkit.commands

import cloud.commandframework.annotations.Argument
import cloud.commandframework.annotations.CommandMethod
import dev.themeinerlp.solarsystem.bukkit.model.BukkitAsteroid

class LoadCommand {

    @CommandMethod("planet load <name>")
    fun load(asteroid: BukkitAsteroid, @Argument("name") name: String) {
        asteroid.service.loadPlanetByName(name)

    }
}
