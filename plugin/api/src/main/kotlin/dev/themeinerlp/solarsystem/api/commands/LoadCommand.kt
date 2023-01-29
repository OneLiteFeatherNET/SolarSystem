package dev.themeinerlp.solarsystem.api.commands

import cloud.commandframework.annotations.Argument
import cloud.commandframework.annotations.CommandDescription
import cloud.commandframework.annotations.CommandMethod
import cloud.commandframework.annotations.Flag
import dev.themeinerlp.solarsystem.api.utils.Asteroid
import dev.themeinerlp.solarsystem.api.world.Planet
import org.bukkit.World
import org.bukkit.World.Environment

class LoadCommand {

    @CommandMethod("planet load <name>")
    fun load(asteroid: Asteroid<World>, @Argument("name") name: String) {
        asteroid.service.loadPlanetByName(name)

    }
}