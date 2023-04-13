package dev.themeinerlp.solarsystem.api.flag

import dev.themeinerlp.solarsystem.api.database.FlagEntity
import dev.themeinerlp.solarsystem.api.database.PlanetEntity
import net.benwoodworth.knbt.NbtTag

abstract class WorldFlag<T,N : NbtTag, F : WorldFlag<T, N, F>>(open val value: T) {

    val flagName: String

    init {
        val strBuilder = StringBuilder()
        val chars = this.javaClass.simpleName.replace("Flag", "").toCharArray()
        chars.forEachIndexed { index, c ->
            if (index == 0) {
                strBuilder.append(c.lowercase())
            } else if (c.isUpperCase()) {
                strBuilder.append('-').append(c.lowercase())
            } else {
                strBuilder.append(c)
            }
        }
        this.flagName = strBuilder.toString()
    }

    abstract fun parse(input: String): F

    abstract fun merge(newValue: T): F

    abstract fun getExample(): String

    protected abstract fun flagOf(value: T): F
    protected abstract fun fromDatabase(value: FlagEntity): F
    protected abstract fun toDatabase(value: F, planet: PlanetEntity): FlagEntity

    protected abstract fun toNbt(value: T): N
    protected abstract fun fromNbt(value: N): T

    fun createFlagInstance(value: T): F {
        return flagOf(value)
    }

    open fun getTabCompletions(): List<String> {
        return listOf()
    }

}