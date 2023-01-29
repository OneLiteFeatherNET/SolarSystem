package dev.themeinerlp.solarsystem.bukkit

import dev.themeinerlp.solarsystem.api.plugin.DatabaseSolarSystem
import dev.themeinerlp.solarsystem.api.service.SolarService
import dev.themeinerlp.solarsystem.bukkit.service.BukkitSolarService

class BukkitSolarSystem : DatabaseSolarSystem() {

    private lateinit var solarService: SolarService
    override fun onEnable() {
        connect(readConfig(), logger)
        this.solarService = BukkitSolarService()
        autoLoadPlanets()
        createCommandSystem()
        registerCommands()
    }

    override fun getSolarService(): SolarService {
        return this.solarService
    }
}