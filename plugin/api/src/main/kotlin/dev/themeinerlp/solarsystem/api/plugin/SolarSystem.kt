package dev.themeinerlp.solarsystem.api.plugin

import dev.themeinerlp.solarsystem.api.service.SolarService

interface SolarSystem {

    fun getSolarService(): SolarService

}