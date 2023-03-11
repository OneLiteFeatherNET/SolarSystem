package dev.themeinerlp.solarsystem.bukkit.commands

import cloud.commandframework.annotations.Argument
import cloud.commandframework.annotations.CommandMethod
import dev.themeinerlp.solarsystem.api.utils.Asteroid
import org.bukkit.World

class LoadCommand {

    @CommandMethod("planet load <name>")
    fun load(asteroid: Asteroid<World>, @Argument("name") name: String) {
        asteroid.service.loadPlanetByName(name)

    }
}
