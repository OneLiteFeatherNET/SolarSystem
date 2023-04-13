package dev.themeinerlp.solarsystem.api.flag.implementations

import dev.themeinerlp.solarsystem.api.database.FlagEntity
import dev.themeinerlp.solarsystem.api.database.FlagTables
import dev.themeinerlp.solarsystem.api.database.PlanetEntity
import dev.themeinerlp.solarsystem.api.flag.FlagParseException
import dev.themeinerlp.solarsystem.api.flag.WorldFlag
import net.benwoodworth.knbt.NbtByte
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

abstract class BooleanFlag<F : WorldFlag<Boolean, NbtByte, F>>(
    override val value: Boolean
) : WorldFlag<Boolean, NbtByte, F>(value) {

    private val positiveValues = listOf("1", "yes", "allow", "true")
    private val negativeValues = listOf("0", "no", "disallow", "false")

    constructor() : this(false)

    override fun getExample(): String {
        return "true"
    }

    override fun parse(input: String): F {
        return if (positiveValues.contains(input.lowercase(Locale.ENGLISH))) {
            this.flagOf(true)
        } else if (negativeValues.contains(input.lowercase(Locale.ENGLISH))) {
            this.flagOf(false)
        } else {
            throw FlagParseException(this, input, "The value must be a boolean value (true/false)")
        }
    }

    override fun merge(newValue: Boolean): F {
        return this.flagOf(value || newValue)
    }

    override fun getTabCompletions(): List<String> {
        return negativeValues + positiveValues
    }

    override fun fromNbt(value: NbtByte): Boolean {
        return value.booleanValue
    }

    override fun toNbt(value: Boolean): NbtByte {
        return if (value) {
            NbtByte(1)
        } else {
            NbtByte(0)
        }
    }

    override fun fromDatabase(value: FlagEntity): F {
        return flagOf(fromNbt(value.value as NbtByte))
    }

    override fun toDatabase(value: F, planet: PlanetEntity): FlagEntity = transaction {
        val flagEntity =
            FlagEntity.find { (FlagTables.planet eq planet.id).and(FlagTables.flag eq flagName) }.firstOrNull()
                ?: FlagEntity.new {
                    this.flag = flagName
                    this.value = toNbt(value.value)
                    this.planet = planet
                }
        return@transaction flagEntity
    }
}