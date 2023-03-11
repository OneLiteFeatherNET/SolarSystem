package dev.themeinerlp.solarsystem.bukkit.commands

import cloud.commandframework.annotations.Argument
import cloud.commandframework.annotations.CommandMethod
import dev.themeinerlp.solarsystem.api.utils.Asteroid
import dev.themeinerlp.solarsystem.api.world.Planet
import org.bukkit.World

class UnloadCommand {

    @CommandMethod("planet unload <name>")
    fun unload(asteroid: Asteroid<World>, @Argument("name", parserName = "planet") planet: Planet<World>) {
        asteroid.service.unloadPlanet(planet)

    }
}
