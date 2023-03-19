package dev.themeinerlp.solarsystem.api.flag

import dev.themeinerlp.solarsystem.api.database.PlanetEntity
import dev.themeinerlp.solarsystem.api.wrapper.player.GameMode
import dev.themeinerlp.solarsystem.api.wrapper.world.Difficulty
import kotlin.reflect.KClass

enum class WorldFlag(
    val defaultValue: Any?,
    val type: KClass<*>
) {
    GAME_MODE("SURVIVAL", GameMode::class),
    DIFFICULTY("EASY", Difficulty::class),
    PVE(true, Boolean::class),
    PVP(true, Boolean::class),
    PERMISSION(null, String::class),
    RESPAWN_PLANET(null, PlanetEntity::class),
}