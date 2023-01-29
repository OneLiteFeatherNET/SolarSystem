package dev.themeinerlp.solarsystem.api.service

import dev.themeinerlp.solarsystem.api.database.PlanetEntity
import dev.themeinerlp.solarsystem.api.world.Planet
import org.bukkit.World.Environment

interface SolarService {

    fun createPlanet(builder: Planet.Builder)

    fun clonePlanet(world: Planet, clonedWorldName: String)

    fun addPlanet(name: String, environment: Environment, generator: String?, useSpawnAdjust: Boolean)

    fun deletePlanet(world: Planet): Boolean
    fun removePlanet(world: Planet): Boolean
    fun unloadPlanet(world: Planet): Boolean

    fun loadPlanetByName(name: String): Planet

    fun getPlanetByName(name: String): Planet

    fun isSolarPlanet(name: String): Boolean
    fun isSolarPlanet(world: Planet): Boolean

    fun getPlanets(): List<PlanetEntity>
    fun getLoadedPlanets(): List<Planet>

}
