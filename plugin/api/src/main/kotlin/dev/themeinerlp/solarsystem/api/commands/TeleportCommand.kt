package dev.themeinerlp.solarsystem.api.commands

import cloud.commandframework.annotations.Argument
import cloud.commandframework.annotations.CommandDescription
import cloud.commandframework.annotations.CommandMethod
import dev.themeinerlp.solarsystem.api.utils.Asteroid
import dev.themeinerlp.solarsystem.api.world.Planet

class TeleportCommand {

    @CommandMethod("planet teleport|tp <name>")
    @CommandDescription("Teleport to a planet")
    fun teleportAsteroid(
        asteroid: Asteroid,
        @Argument(
            value = "name",
            parserName = "planet"
        )
        planet: Planet,
    ) {
        val world = planet.getOriginWorld() ?: return
        asteroid.player.teleportAsync(world.spawnLocation)
    }

}