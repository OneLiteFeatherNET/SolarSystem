package dev.themeinerlp.solarsystem.bukkit.commands

import cloud.commandframework.annotations.Argument
import cloud.commandframework.annotations.CommandMethod
import dev.themeinerlp.solarsystem.api.world.Planet
import dev.themeinerlp.solarsystem.bukkit.model.BukkitAsteroid
import org.bukkit.World

class UnloadCommand {

    @CommandMethod("planet unload <name>")
    fun unload(asteroid: BukkitAsteroid, @Argument("name", parserName = "planet") planet: Planet<World>) {
        asteroid.service.unloadPlanet(planet)

    }
}
