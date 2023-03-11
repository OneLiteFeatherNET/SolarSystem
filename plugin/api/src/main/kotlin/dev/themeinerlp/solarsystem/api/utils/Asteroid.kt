package dev.themeinerlp.solarsystem.api.utils

import dev.themeinerlp.solarsystem.api.service.SolarService
import dev.themeinerlp.solarsystem.api.world.Planet
import net.kyori.adventure.audience.Audience
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

open class Asteroid<T>(
    val commandSender: CommandSender,
    val player: Player,
    val service: SolarService<T>,
) : Audience {
    val currentPlanet: Planet<T>
        get() {
            return service.getPlanetByName(player.world.name)
        }
}