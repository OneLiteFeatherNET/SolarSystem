package dev.themeinerlp.solarsystem.bukkit.listener

import dev.themeinerlp.solarsystem.api.service.SolarService
import dev.themeinerlp.solarsystem.bukkit.extensions.isSolarWorld
import dev.themeinerlp.solarsystem.bukkit.extensions.toPlanet
import org.bukkit.World
import org.bukkit.entity.Animals
import org.bukkit.entity.Monster
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.CreatureSpawnEvent

class SolarEntityListener(
    val solarService: SolarService<World>,
) : Listener {
    @EventHandler
    fun creatureSpawn(event: CreatureSpawnEvent) {
        if (event.spawnReason == CreatureSpawnEvent.SpawnReason.CUSTOM ||
            event.spawnReason == CreatureSpawnEvent.SpawnReason.SPAWNER_EGG ||
            event.spawnReason == CreatureSpawnEvent.SpawnReason.BREEDING
        ) return
        if (event.isCancelled) return

        val world = event.entity.world

        if (!world.isSolarWorld(solarService)) return
        val planet = world.toPlanet(solarService)
        if (!planet.isMonsterSpawningEnabled() && event.entity is Monster) {
            event.isCancelled = true
        }
        if (!planet.isAnimalsSpawningEnabled() && event.entity is Animals) {
            event.isCancelled = true
        }
    }


}