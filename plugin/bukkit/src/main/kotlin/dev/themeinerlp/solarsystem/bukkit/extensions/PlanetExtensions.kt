package dev.themeinerlp.solarsystem.bukkit.extensions

import dev.themeinerlp.solarsystem.api.database.PlanetEntity
import org.bukkit.WorldCreator

fun PlanetEntity.getBukkitCreator(): WorldCreator {
    return org.bukkit.WorldCreator.name(name).generator(generator).environment(environment.toBukkit()).seed(seed)
        .type(worldType.toBukkit())
}