package dev.themeinerlp.solarsystem.bukkit.service

import dev.themeinerlp.solarsystem.api.database.PlanetTables
import dev.themeinerlp.solarsystem.api.database.PlanetEntity
import dev.themeinerlp.solarsystem.api.service.SolarService
import dev.themeinerlp.solarsystem.api.world.Planet
import dev.themeinerlp.solarsystem.bukkit.world.BukkitPlanet
import org.bukkit.Bukkit
import org.bukkit.World
import org.jetbrains.exposed.sql.transactions.transaction
import java.lang.RuntimeException
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.Path
import kotlin.io.path.deleteRecursively

class BukkitSolarService : SolarService<World> {
    override fun createPlanet(builder: Planet.Builder) = transaction {
        val createdWorld = builder.worldCreator.createWorld()
        if (createdWorld != null) {
            if (PlanetEntity.find { PlanetTables.name eq createdWorld.name }.empty()) {
                PlanetEntity.new {
                    name = createdWorld.name
                    environment = createdWorld.environment
                    seed = createdWorld.seed
                }
            }

        }
    }

    override fun loadPlanetByName(name: String): Planet<World> = transaction {
        val bukkitWorld = Bukkit.getWorld(name)
        val selectedPlanet = PlanetEntity.find { PlanetTables.name eq name }.firstOrNull()
        return@transaction if (selectedPlanet != null) {
            if (bukkitWorld != null) {
                return@transaction BukkitPlanet.width(bukkitWorld, selectedPlanet)
            } else {
                val world = Bukkit.createWorld(selectedPlanet.worldCreator)
                if (world != null) {
                    return@transaction BukkitPlanet.width(world, selectedPlanet)
                }
                throw RuntimeException()
            }
        } else {
            throw RuntimeException()
        }

    }

    override fun getPlanetByName(name: String): Planet<World> = transaction {
        val bukkitWorld = Bukkit.getWorld(name)
        val selectedPlanet = PlanetEntity.find { PlanetTables.name eq name }.firstOrNull()
        if (selectedPlanet != null) {
            if (bukkitWorld != null) {
                return@transaction BukkitPlanet.width(bukkitWorld, selectedPlanet)
            }
            throw RuntimeException()
        } else {
            throw RuntimeException()
        }
    }

    override fun isSolarPlanet(name: String): Boolean = !PlanetEntity.find { PlanetTables.name eq name }.empty()

    override fun isSolarPlanet(planet: Planet<World>): Boolean =
        !PlanetEntity.find { PlanetTables.name eq planet.getName() }.empty()

    @OptIn(ExperimentalPathApi::class)
    override fun deletePlanet(planet: Planet<World>): Boolean = transaction {
        planet.getOriginWorld() ?: throw RuntimeException()
        val success = Bukkit.unloadWorld(planet.getOriginWorld()!!, false)
        planet.getEntity().delete()
        if (success) {
            Path(planet.getName()).deleteRecursively()
        }
        return@transaction success
    }

    override fun clonePlanet(planet: Planet<World>, clonedPlanetName: String) {
        TODO("Not yet implemented")
    }
}