package dev.themeinerlp.solarsystem.api.service

import dev.themeinerlp.solarsystem.api.database.PlanetEntity
import dev.themeinerlp.solarsystem.api.world.Planet
import org.bukkit.World.Environment

interface SolarService<T> {

    fun createPlanet(builder: Planet.Builder)

    fun clonePlanet(world: Planet<T>, clonedWorldName: String)

    fun addPlanet(name: String, environment: Environment, generator: String?, useSpawnAdjust: Boolean)

    fun deletePlanet(world: Planet<T>): Boolean
    fun removePlanet(world: Planet<T>): Boolean
    fun unloadPlanet(world: Planet<T>): Boolean

    fun loadPlanetByName(name: String): Planet<T>

    fun getPlanetByName(name: String): Planet<T>

    fun isSolarPlanet(name: String): Boolean
    fun isSolarPlanet(world: Planet<T>): Boolean

    fun getPlanets(): List<PlanetEntity>
    fun getLoadedPlanets(): List<Planet<T>>

}
