package dev.themeinerlp.solarsystem.bukkit.service

import dev.themeinerlp.solarsystem.api.database.PlanetEntity
import dev.themeinerlp.solarsystem.api.database.PlanetTables
import dev.themeinerlp.solarsystem.api.service.SolarService
import dev.themeinerlp.solarsystem.api.utils.BANNED_WORLD_NAMES
import dev.themeinerlp.solarsystem.api.world.Planet
import dev.themeinerlp.solarsystem.api.world.PlanetOption
import dev.themeinerlp.solarsystem.api.wrapper.world.Environment
import dev.themeinerlp.solarsystem.api.wrapper.world.GameRule
import dev.themeinerlp.solarsystem.bukkit.extensions.getBukkitCreator
import dev.themeinerlp.solarsystem.bukkit.extensions.toBukkit
import dev.themeinerlp.solarsystem.bukkit.extensions.toSolar
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
            creator.environment(environment!!.toBukkit())
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
                    environment = createdWorld.environment.toSolar()
                    seed = createdWorld.seed
                }
            }

        }
    }

    override fun addPlanet(name: String, environment: Environment, generator: String?, useSpawnAdjust: Boolean) =
        transaction {
            PlanetEntity.find { PlanetTables.name eq name }.firstOrNull() ?: kotlin.run {
                val creator = WorldCreator(name).environment(environment.toBukkit())
                if (generator != null) {
                    creator.generator(generator)
                }
                val world = creator.createWorld() ?: return@transaction
                PlanetEntity.new {
                    this.name = world.name
                    this.seed = world.seed
                    this.time = world.time
                    this.environment = world.environment.toSolar()
                    this.adjustSpawn = adjustSpawn
                    this.generator = generator
                }
            }
            Unit

        }

    override fun removePlanet(planet: Planet<World>): Boolean = transaction {
        planet.getEntity().autoLoad = false
        unloadPlanet(planet)
    }

    override fun unloadPlanet(planet: Planet<World>): Boolean {
        val originWorld = planet.getOriginWorld() ?: return false
        return Bukkit.unloadWorld(originWorld, true)
    }

    override fun loadPlanetByName(name: String): Planet<World> = transaction {
        val bukkitWorld = Bukkit.getWorld(name)
        val selectedPlanet = PlanetEntity.find { PlanetTables.name eq name }.firstOrNull()
        if (selectedPlanet != null) {
            if (bukkitWorld != null) {
                return@transaction BukkitPlanet.with(bukkitWorld, selectedPlanet)
            } else {
                val world = Bukkit.createWorld(selectedPlanet.getBukkitCreator())
                if (world != null) {
                    return@transaction BukkitPlanet.with(world, selectedPlanet)
                }
                throw NullPointerException("Bukkit world is null")
            }
        } else {
            throw NullPointerException("Selected planet is null")
        }
    }

    override fun getPlanetByName(name: String): Planet<World> = transaction {
        val bukkitWorld = Bukkit.getWorld(name)
        val selectedPlanet = PlanetEntity.find { PlanetTables.name eq name }.firstOrNull()
        if (selectedPlanet != null) {
            if (bukkitWorld != null) {
                return@transaction BukkitPlanet.with(bukkitWorld, selectedPlanet)
            }
            throw NullPointerException("Bukkit world is null")
        } else {
            throw NullPointerException("Selected planet is null")
        }
    }

    override fun isSolarPlanet(name: String): Boolean = transaction {
        return@transaction !PlanetEntity.find { PlanetTables.name eq name }.empty()
    }

    override fun updateOption(planet: Planet<World>, option: PlanetOption, value: Boolean) {
        when (option) {
            PlanetOption.Monster -> planet.setMonsterSpawningEnabled(value)
            PlanetOption.Animal -> planet.setAnimalsSpawningEnabled(value)
            else -> {}
        }
    }

    override fun changeGameRule(planet: Planet<World>, rule: GameRule, value: Any) {
        val bukkitWorld = planet.getOriginWorld() ?: throw NullPointerException("Bukkit world is empty")
        val bukkitRule = org.bukkit.GameRule.getByName(rule.vanillaName) as org.bukkit.GameRule<Any>
        bukkitWorld.setGameRule<Any>(bukkitRule, value)
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
        planet.getOriginWorld() ?: throw NullPointerException("World is null")
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
