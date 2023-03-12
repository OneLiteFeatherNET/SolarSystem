package dev.themeinerlp.solarsystem.bukkit.commands

import cloud.commandframework.annotations.Argument
import cloud.commandframework.annotations.CommandDescription
import cloud.commandframework.annotations.CommandMethod
import dev.themeinerlp.solarsystem.api.world.Planet
import dev.themeinerlp.solarsystem.bukkit.model.BukkitAsteroid
import org.bukkit.World

class DeleteCommand {
    @CommandMethod("planet delete <name>")
    @CommandDescription("Delete a world forever!")
    fun deletePlanet(
        asteroid: BukkitAsteroid,
        @Argument(
            value = "name",
            parserName = "planet"
        )
        planet: Planet<World>,
    ) {
        asteroid.service.deletePlanet(planet)
    }
}
