package dev.themeinerlp.solarsystem.api.commands

import cloud.commandframework.annotations.Argument
import cloud.commandframework.annotations.CommandDescription
import cloud.commandframework.annotations.CommandMethod
import dev.themeinerlp.solarsystem.api.utils.Asteroid
import dev.themeinerlp.solarsystem.api.world.Planet

class DeleteCommand {
    @CommandMethod("planet delete <name>")
    @CommandDescription("Delete a world forever!")
    fun deletePlanet(
        asteroid: Asteroid,
        @Argument(
            value = "name",
            parserName = "planet"
        )
        planet: Planet,
    ) {
        asteroid.service.deletePlanet(planet)
    }
}