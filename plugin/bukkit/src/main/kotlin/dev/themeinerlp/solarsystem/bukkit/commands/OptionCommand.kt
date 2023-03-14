package dev.themeinerlp.solarsystem.bukkit.commands

import cloud.commandframework.annotations.Argument
import cloud.commandframework.annotations.CommandMethod
import cloud.commandframework.annotations.CommandPermission
import dev.themeinerlp.solarsystem.api.world.Planet
import dev.themeinerlp.solarsystem.api.world.PlanetOption
import dev.themeinerlp.solarsystem.bukkit.model.BukkitAsteroid
import dev.themeinerlp.solarsystem.bukkit.utils.COMMANDS_IMPORT
import org.bukkit.World

class OptionCommand {
    @CommandMethod("planet option <name> <option> <value>")
    @CommandPermission(COMMANDS_IMPORT)
    fun import(
        asteroid: BukkitAsteroid,
        @Argument(value = "name", parserName = "planet") planet: Planet<World>,
        @Argument("option") option: PlanetOption,
        @Argument("value") value: Boolean,
    ) {
        asteroid.service.updateOption(planet, option, value)
    }
}