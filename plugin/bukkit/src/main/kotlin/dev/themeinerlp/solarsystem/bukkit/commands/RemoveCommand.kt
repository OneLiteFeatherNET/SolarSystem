package dev.themeinerlp.solarsystem.bukkit.commands

import cloud.commandframework.annotations.Argument
import cloud.commandframework.annotations.CommandMethod
import cloud.commandframework.annotations.CommandPermission
import dev.themeinerlp.solarsystem.api.world.Planet
import dev.themeinerlp.solarsystem.bukkit.model.BukkitAsteroid
import dev.themeinerlp.solarsystem.bukkit.utils.COMMANDS_REMOVE
import org.bukkit.World

class RemoveCommand {

    @CommandMethod("planet remove <name>")
    @CommandPermission(COMMANDS_REMOVE)
    fun remove(asteroid: BukkitAsteroid, @Argument("name", parserName = "planet") planet: Planet<World>) {
        asteroid.service.removePlanet(planet)

    }
}
