package dev.themeinerlp.solarsystem.bukkit.commands

import cloud.commandframework.annotations.Argument
import cloud.commandframework.annotations.CommandDescription
import cloud.commandframework.annotations.CommandMethod
import dev.themeinerlp.solarsystem.api.world.Planet
import dev.themeinerlp.solarsystem.bukkit.model.BukkitAsteroid
import org.bukkit.World

class TeleportCommand {

    @CommandMethod("planet teleport|tp <name>")
    @CommandDescription("Teleport to a planet")
    fun teleportAsteroid(
        asteroid: BukkitAsteroid,
        @Argument(
            value = "name",
            parserName = "planet"
        )
        planet: Planet<World>,
    ) {
        val world = planet.getOriginWorld() ?: return
        asteroid.entity.teleportAsync(world.spawnLocation)
    }

}
