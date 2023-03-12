package dev.themeinerlp.solarsystem.bukkit.service

import dev.themeinerlp.solarsystem.api.database.PlanetEntity
import dev.themeinerlp.solarsystem.api.database.PlanetTables
import dev.themeinerlp.solarsystem.api.service.SolarService
import dev.themeinerlp.solarsystem.api.utils.BANNED_WORLD_NAMES
import dev.themeinerlp.solarsystem.api.world.Planet
import dev.themeinerlp.solarsystem.bukkit.world.BukkitPlanet
import org.bukkit.Bukkit
import org.bukkit.World
import org.bukkit.WorldCreator
import org.bukkit.WorldType
import org.jetbrains.exposed.sql.transactions.transaction
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.Path
import kotlin.io.path.deleteRecursively

class BukkitSolarService : SolarService<World> {

    fun Planet.Builder.worldCreator(): WorldCreator {
        if (name == null) {
            throw NullPointerException()
        }
        if (name in BANNED_WORLD_NAMES) {
            throw IllegalArgumentException()
        }
        val creator = WorldCreator.name(name!!)

        WorldType.getByName(worldType.bukkitValue)?.let { creator.type(it) }
        if (environment != null) {
            creator.environment(environment!!)
        }
        if (seed != null) {
            creator.seed(seed!!)
        }
        if (generateStructures) {
            creator.generateStructures(generateStructures)
        }
        if (generator != null) {
            creator.generator(generator)
        }
        return creator
    }

    override fun createPlanet(builder: Planet.Builder) = transaction {

        val createdWorld = builder.worldCreator().createWorld()
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

    override fun addPlanet(name: String, environment: World.Environment, generator: String?, useSpawnAdjust: Boolean) =
        transaction {
            PlanetEntity.find { PlanetTables.name eq name }.firstOrNull() ?: kotlin.run {
                val creator = WorldCreator(name).environment(environment)
                if (generator != null) {
                    creator.generator(generator)
                }
                val world = creator.createWorld() ?: return@transaction
                PlanetEntity.new {
                    this.name = world.name
                    this.seed = world.seed
                    this.time = world.time
                    this.environment = world.environment
                    this.adjustSpawn = adjustSpawn
                    this.generator = generator
                }
            }
            Unit

        }

    override fun removePlanet(world: Planet<World>): Boolean = transaction {
        world.getEntity().autoLoad = false
        unloadPlanet(world)
    }

    override fun unloadPlanet(world: Planet<World>): Boolean {
        val world = world.getOriginWorld() ?: return false
        return Bukkit.unloadWorld(world, true)
    }

    override fun loadPlanetByName(name: String): Planet<World> = transaction {
        val bukkitWorld = Bukkit.getWorld(name)
        val selectedPlanet = PlanetEntity.find { PlanetTables.name eq name }.firstOrNull()
        return@transaction if (selectedPlanet != null) {
            if (bukkitWorld != null) {
                return@transaction BukkitPlanet.with(bukkitWorld, selectedPlanet)
            } else {
                val world = Bukkit.createWorld(selectedPlanet.worldCreator)
                if (world != null) {
                    return@transaction BukkitPlanet.with(world, selectedPlanet)
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
                return@transaction BukkitPlanet.with(bukkitWorld, selectedPlanet)
            }
            throw RuntimeException()
        } else {
            throw RuntimeException()
        }
    }

    override fun isSolarPlanet(name: String): Boolean = transaction {
        return@transaction !PlanetEntity.find { PlanetTables.name eq name }.empty()
    }

    override fun getPlanets(): List<PlanetEntity> = transaction {
        return@transaction PlanetEntity.all().toList()
    }

    override fun getLoadedPlanets(): List<Planet<World>> = transaction {
        return@transaction PlanetEntity.all().filter { Bukkit.getWorld(it.name) != null }
            .map { BukkitPlanet.with(Bukkit.getWorld(it.name)!!, it) }
    }

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
