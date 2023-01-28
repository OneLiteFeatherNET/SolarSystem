package dev.themeinerlp.solarsystem.api.service

import dev.themeinerlp.solarsystem.api.world.Planet

interface SolarService<T> {

    fun createPlanet(builder: Planet.Builder)

    fun clonePlanet(world: Planet<T>, clonedWorldName: String)

    fun deletePlanet(world: Planet<T>): Boolean

    fun loadPlanetByName(name: String): Planet<T>

    fun getPlanetByName(name: String): Planet<T>

    fun isSolarPlanet(name: String): Boolean
    fun isSolarPlanet(world: Planet<T>): Boolean

}