package dev.themeinerlp.solarsystem.bukkit.commands

import cloud.commandframework.annotations.Argument
import cloud.commandframework.annotations.CommandDescription
import cloud.commandframework.annotations.CommandMethod
import dev.themeinerlp.solarsystem.api.utils.Asteroid
import dev.themeinerlp.solarsystem.api.world.Planet
import org.bukkit.World

class TeleportCommand {

    @CommandMethod("planet teleport|tp <name>")
    @CommandDescription("Teleport to a planet")
    fun teleportAsteroid(
        asteroid: Asteroid<World>,
        @Argument(
            value = "name",
            parserName = "planet"
        )
        planet: Planet<World>,
    ) {
        val world = planet.getOriginWorld() ?: return
        asteroid.player.teleportAsync(world.spawnLocation)
    }

}
