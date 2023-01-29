package dev.themeinerlp.solarsystem.api.utils

import dev.themeinerlp.solarsystem.api.service.SolarService
import dev.themeinerlp.solarsystem.api.world.Planet
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

data class Asteroid(
    val commandSender: CommandSender,
    val player: Player,
    val service: SolarService,
) {
    val currentPlanet: Planet?
        get() {
            return service.getPlanetByName(player.world.name)
        }
}