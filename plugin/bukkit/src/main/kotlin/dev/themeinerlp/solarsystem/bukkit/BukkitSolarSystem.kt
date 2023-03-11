package dev.themeinerlp.solarsystem.bukkit

import dev.themeinerlp.solarsystem.api.config.SQLConfig
import dev.themeinerlp.solarsystem.api.plugin.DatabaseSolarSystem
import dev.themeinerlp.solarsystem.api.service.SolarService
import org.bukkit.World
import java.util.logging.Logger

class BukkitSolarSystem(
    val solarService: SolarService<World>, config: SQLConfig, logger: Logger,
) : DatabaseSolarSystem<World>(config, logger) {
    override fun getJavaSolarService(): SolarService<World> {
        return this.solarService
    }
}
