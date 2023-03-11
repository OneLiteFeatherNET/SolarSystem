package dev.themeinerlp.solarsystem.bukkit.world

import dev.themeinerlp.solarsystem.api.database.PlanetEntity
import dev.themeinerlp.solarsystem.api.world.Planet
import dev.themeinerlp.solarsystem.api.wrapper.player.GameMode
import dev.themeinerlp.solarsystem.api.wrapper.world.Difficulty
import dev.themeinerlp.solarsystem.api.wrapper.world.WorldType
import dev.themeinerlp.solarsystem.bukkit.mapper.toBukkit
import dev.themeinerlp.solarsystem.bukkit.mapper.toSolar
import org.bukkit.World
import org.bukkit.World.Environment
import org.jetbrains.exposed.sql.transactions.transaction

class BukkitPlanet() : Planet<World> {
    private lateinit var world: World
    private lateinit var plantEntity: PlanetEntity

    private constructor(
        world: World,
        plantEntity: PlanetEntity,
    ) : this() {
        this.world = world
        this.plantEntity = plantEntity
    }

    companion object {
        fun width(
            world: World,
            plantEntity: PlanetEntity,
        ) = BukkitPlanet(world, plantEntity)
    }

    override fun getOriginWorld(): World {
        return world
    }

    override fun getName(): String = this.plantEntity.name

    override fun getDifficulty(): Difficulty = this.plantEntity.difficulty.toSolar()

    override fun getEnvironment(): Environment = this.plantEntity.environment

    override fun setEnvironment(environment: Environment) = transaction {
        plantEntity.environment = environment
    }

    override fun getWorldType(): WorldType = this.plantEntity.worldType.toSolar()

    override fun setWorldType(worldType: WorldType) = transaction {
        plantEntity.worldType = worldType.toBukkit()
    }

    override fun getSeed(): Long = this.plantEntity.seed

    override fun setSeed(seed: Long) = transaction {
        plantEntity.seed = seed
    }

    override fun getGenerator(): String? = this.plantEntity.generator

    override fun setGenerator(generator: String?) = transaction {
        plantEntity.generator = generator
    }

    override fun getPropertyHelp(property: String): String {
        TODO("Not yet implemented")
    }

    override fun getPropertyValue(property: String): String {
        TODO("Not yet implemented")
    }

    override fun setPropertyValue(property: String, value: Any?): Boolean {
        TODO("Not yet implemented")
    }

    override fun getPermissibleName(): String {
        TODO("Not yet implemented")
    }

    override fun getAccessPermission(): String {
        TODO("Not yet implemented")
    }

    override fun getAlias(): String? = this.plantEntity.alias

    override fun setAlias(alias: String?) = transaction {
        plantEntity.alias = alias
    }

    override fun isMonsterSpawningEnabled(): Boolean = this.plantEntity.monsterSpawning

    override fun setMonsterSpawningEnabled(enableMonsterSpawning: Boolean) = transaction {
        plantEntity.monsterSpawning = enableMonsterSpawning
    }

    override fun isPvPEnabled(): Boolean = this.plantEntity.pvp

    override fun setPvPEnabled(enablePvP: Boolean) = transaction {
        plantEntity.pvp = enablePvP
    }

    override fun isHidden(): Boolean = this.plantEntity.hidden
    override fun setHidden(hidden: Boolean) = transaction {
        plantEntity.hidden = hidden
    }

    override fun isWeatherEnabled(): Boolean = this.plantEntity.weather

    override fun setWeatherEnabled(enableWeather: Boolean) = transaction {
        plantEntity.weather = enableWeather
    }

    override fun setHungerEnabled(enableHunger: Boolean) = transaction {
        plantEntity.hunger = enableHunger
    }

    override fun isHungerEnabled(): Boolean = this.plantEntity.hunger

    override fun setAutoHealEnabled(enableAutoHeal: Boolean) = transaction {
        plantEntity.autoHeal = enableAutoHeal
    }

    override fun isAutoHealEnabled(): Boolean = this.plantEntity.autoHeal

    override fun setAdjustSpawnEnabled(enableAdjustSpawn: Boolean) = transaction {
        plantEntity.adjustSpawn = enableAdjustSpawn
    }

    override fun isAdjustSpawnEnabled(): Boolean = this.plantEntity.adjustSpawn

    override fun setAutoLoadEnabled(enableAutoLoad: Boolean) = transaction {
        plantEntity.autoLoad = enableAutoLoad
    }

    override fun isAutoLoadEnabled(): Boolean = this.plantEntity.autoLoad

    override fun setBedRespawnEnabled(enableBedRespawn: Boolean) = transaction {
        plantEntity.bedRespawn = enableBedRespawn
    }

    override fun isBedRespawnEnabled(): Boolean = this.plantEntity.bedRespawn

    override fun setAllowFlight(allowFlight: Boolean) = transaction {
        plantEntity.allowFlight = allowFlight

    }

    override fun getAllowFlight(): Boolean = this.plantEntity.allowFlight

    override fun setTime(time: Long) = transaction {
        plantEntity.time = time
    }

    override fun getTime(): Long = this.plantEntity.time

    override fun setPlayerLimit(limit: Int) = transaction {
        plantEntity.playerLimit = limit
    }

    override fun getPlayerLimit(): Int = this.plantEntity.playerLimit

    override fun getRespawnWorld(): Planet<World> {
        TODO("Not yet implemented")
    }

    override fun setRespawnWorld(world: Planet<World>) {
        TODO("Not yet implemented")
    }

    override fun getGameMode(): GameMode = this.plantEntity.gamemode.toSolar()

    override fun setGameMode(mode: GameMode) = transaction {
        plantEntity.gamemode = mode.toBukkit()
    }

    override fun getEntity(): PlanetEntity = this.plantEntity
}
