package dev.themeinerlp.solarsystem.bukkit.extensions

import dev.themeinerlp.solarsystem.api.service.SolarService
import dev.themeinerlp.solarsystem.api.world.Planet
import org.bukkit.World

fun World.isSolarWorld(solarService: SolarService<World>): Boolean {
    return solarService.isSolarPlanet(this.name)
}

fun World.toPlanet(solarService: SolarService<World>): Planet<World> {
    return solarService.getPlanetByName(this.name)
}