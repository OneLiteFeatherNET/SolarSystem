package dev.themeinerlp.solarsystem.api.service

import dev.themeinerlp.solarsystem.api.database.PlanetEntity
import dev.themeinerlp.solarsystem.api.world.Planet
import dev.themeinerlp.solarsystem.api.world.PlanetOption
import dev.themeinerlp.solarsystem.api.wrapper.world.Environment
import dev.themeinerlp.solarsystem.api.wrapper.world.GameRule

interface SolarService<T> {

    fun createPlanet(builder: Planet.Builder)

    fun clonePlanet(planet: Planet<T>, clonedWorldName: String)

    fun addPlanet(name: String, environment: Environment, generator: String?, useSpawnAdjust: Boolean)

    fun deletePlanet(planet: Planet<T>): Boolean
    fun removePlanet(planet: Planet<T>): Boolean
    fun unloadPlanet(planet: Planet<T>): Boolean

    fun loadPlanetByName(name: String): Planet<T>

    fun getPlanetByName(name: String): Planet<T>

    fun isSolarPlanet(name: String): Boolean
    fun isSolarPlanet(plant: Planet<T>): Boolean

    fun changeGameRule(planet: Planet<T>, rule: GameRule, value: Any)
    fun updateOption(planet: Planet<T>, option: PlanetOption, value: Boolean)

    fun getPlanets(): List<PlanetEntity>
    fun getLoadedPlanets(): List<Planet<T>>

}
