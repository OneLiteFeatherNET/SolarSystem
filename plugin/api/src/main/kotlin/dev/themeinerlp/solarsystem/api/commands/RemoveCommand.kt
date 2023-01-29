package dev.themeinerlp.solarsystem.api.commands

import cloud.commandframework.annotations.Argument
import cloud.commandframework.annotations.CommandMethod
import dev.themeinerlp.solarsystem.api.utils.Asteroid
import dev.themeinerlp.solarsystem.api.world.Planet

class RemoveCommand {

    @CommandMethod("planet remove <name>")
    fun remove(asteroid: Asteroid, @Argument("name", parserName = "planet") planet: Planet) {
        asteroid.service.removePlanet(planet)

    }
}