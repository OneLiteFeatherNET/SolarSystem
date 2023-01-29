package dev.themeinerlp.solarsystem.api.commands

import cloud.commandframework.annotations.Argument
import cloud.commandframework.annotations.CommandMethod
import dev.themeinerlp.solarsystem.api.utils.Asteroid

class LoadCommand {

    @CommandMethod("planet load <name>")
    fun load(asteroid: Asteroid, @Argument("name") name: String) {
        asteroid.service.loadPlanetByName(name)

    }
}