package dev.themeinerlp.solarsystem.api.service

import dev.themeinerlp.solarsystem.api.world.Planet

interface SolarService<T> {

    fun createWorld(builder: Planet.Builder)

    fun cloneWorld(world: Planet<T>, clonedWorldName: String)

    fun deleteWorld(world: Planet<T>): Boolean

    fun loadWorldByName(name: String): Planet<T>

    fun getWorldByName(name: String): Planet<T>

    fun isWorldSolarWorld(name: String): Boolean
    fun isWorldSolarWorld(world: Planet<T>): Boolean

}