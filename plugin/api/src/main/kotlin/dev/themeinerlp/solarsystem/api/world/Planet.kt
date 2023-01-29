package dev.themeinerlp.solarsystem.api.world

import dev.themeinerlp.solarsystem.api.database.PlanetEntity
import dev.themeinerlp.solarsystem.api.utils.BANNED_WORLD_NAMES
import org.bukkit.Difficulty
import org.bukkit.GameMode
import org.bukkit.World
import org.bukkit.WorldCreator
import org.bukkit.WorldType

interface Planet {

    fun getOriginWorld(): World?

    fun getEntity(): PlanetEntity

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

    fun setRespawnWorld(world: Planet)

    fun getRespawnWorld(): Planet?

    fun getGameMode(): GameMode

    fun setGameMode(mode: GameMode)

    class Builder(
        var name: String? = null,
        var seed: Long? = null,
        var environment: World.Environment? = null,
        var generateStructures: Boolean = true,
        var generator: String? = null,
        var useSpawnAdjust: Boolean = true,
        var worldType: WorldType = WorldType.NORMAL
    ) {
        fun name(name: String) = apply { this.name = name }
        fun seed(seed: Long) = apply { this.seed = seed }
        fun environment(environment: World.Environment) = apply { this.environment = environment }
        fun generateStructures(generateStructures: Boolean) = apply { this.generateStructures = generateStructures }
        fun worldType(worldType: WorldType) = apply { this.worldType = worldType }
        fun generator(generator: String?) = apply { this.generator = generator }
        fun useSpawnAdjust(spawnAdjust: Boolean) = apply { this.useSpawnAdjust = spawnAdjust }

        val worldCreator: WorldCreator
            get() {
                if (name == null) {
                    throw NullPointerException()
                }
                if (name in BANNED_WORLD_NAMES) {
                    throw IllegalArgumentException()
                }
                val creator = WorldCreator.name(name!!)
                creator.type(worldType)
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
    }

}