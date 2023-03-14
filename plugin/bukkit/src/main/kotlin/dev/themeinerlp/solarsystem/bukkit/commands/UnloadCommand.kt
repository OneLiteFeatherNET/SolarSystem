package dev.themeinerlp.solarsystem.bukkit.commands

import cloud.commandframework.annotations.Argument
import cloud.commandframework.annotations.CommandMethod
import cloud.commandframework.annotations.CommandPermission
import dev.themeinerlp.solarsystem.api.world.Planet
import dev.themeinerlp.solarsystem.bukkit.model.BukkitAsteroid
import dev.themeinerlp.solarsystem.bukkit.utils.COMMANDS_UNLOAD
import org.bukkit.World

class UnloadCommand {

    @CommandMethod("planet unload <name>")
    @CommandPermission(COMMANDS_UNLOAD)
    fun unload(asteroid: BukkitAsteroid, @Argument("name", parserName = "planet") planet: Planet<World>) {
        asteroid.service.unloadPlanet(planet)
    }
}
