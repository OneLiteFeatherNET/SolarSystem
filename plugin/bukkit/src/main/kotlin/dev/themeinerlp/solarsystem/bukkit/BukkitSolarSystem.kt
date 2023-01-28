package dev.themeinerlp.solarsystem.bukkit

import dev.themeinerlp.solarsystem.api.plugin.DatabaseSolarSystem
import dev.themeinerlp.solarsystem.api.service.SolarService
import dev.themeinerlp.solarsystem.api.world.Planet
import dev.themeinerlp.solarsystem.bukkit.service.BukkitSolarService
import org.bukkit.World
import org.bukkit.plugin.java.JavaPlugin

class BukkitSolarSystem : DatabaseSolarSystem<World>() {

    private lateinit var solarService: SolarService<World>
    override fun onEnable() {
        connect(readConfig(), logger)
        this.solarService = BukkitSolarService()
        autoLoadPlanets()
        this.solarService.createPlanet(Planet.Builder().name("help").environment(World.Environment.NETHER))
        createCommandSystem()
        registerCommands()
    }
    override fun getSolarService(): SolarService<World> {
        return this.solarService
    }
}