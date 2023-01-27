package dev.themeinerlp.solarsystem.api.world

import org.bukkit.Difficulty
import org.bukkit.GameMode
import org.bukkit.World
import org.bukkit.WorldType

interface Planet<T> {

    fun getOriginWorld(): T?

    fun getName(): String

    fun getDifficulty(): Difficulty

    fun getEnvironment(): World.Environment

    fun setEnvironment(environment: World.Environment)

    fun getWorldType(): WorldType

    fun setWorldType(worldType: WorldType)

    fun getSeed(): Long

    fun setSeed(seed: Long)

    fun getGenerator(): String?

    fun setGenerator(generator: String?)

    // START Config
    fun getPropertyHelp(property: String): String

    fun getPropertyValue(property: String): String

    fun setPropertyValue(property: String, value: Any?): Boolean
    // START End

    // START Permission
    fun getPermissibleName(): String

    fun getAccessPermission(): String

    // END Permission
    fun getAlias(): String?

    fun setAlias(alias: String?)

    fun isMonsterSpawningEnabled(): Boolean

    fun setMonsterSpawningEnabled(enableMonsterSpawning: Boolean)

    fun isPvPEnabled(): Boolean

    fun setPvPEnabled(enablePvP: Boolean)

    fun isHidden(): Boolean

    fun setHidden(hidden: Boolean)

    fun isWeatherEnabled(): Boolean

    fun setWeatherEnabled(enableWeather: Boolean)

    fun setHungerEnabled(enableHunger: Boolean)

    fun isHungerEnabled(): Boolean

    fun setAutoHealEnabled(enableAutoHeal: Boolean)

    fun isAutoHealEnabled(): Boolean

    fun setAdjustSpawnEnabled(enableAdjustSpawn: Boolean)

    fun isAdjustSpawnEnabled(): Boolean

    fun setAutoLoadEnabled(enableAutoLoad: Boolean)

    fun isAutoLoadEnabled(): Boolean

    fun setBedRespawnEnabled(enableBedRespawn: Boolean)

    fun isBedRespawnEnabled(): Boolean

    fun setAllowFlight(allowFlight: Boolean)

    fun getAllowFlight(): Boolean

    fun setTime(time: Long)

    fun getTime(): Long

    fun setPlayerLimit(limit: Int)

    fun getPlayerLimit(): Int

    fun setRespawnWorld(world: Planet<T>)

    fun getRespawnWorld(): Planet<T>

    fun getGameMode(): GameMode

    fun setGameMode(mode: GameMode)

    class Builder(
        var name: String? = null,
        var difficulty: Difficulty? = null,
        var environment: World.Environment? = null,
    ) {
        fun name(name: String) = apply { this.name = name }
        fun difficulty(difficulty: Difficulty) = apply { this.difficulty = difficulty }
        fun environment(environment: World.Environment) = apply { this.environment = environment }

    }

}