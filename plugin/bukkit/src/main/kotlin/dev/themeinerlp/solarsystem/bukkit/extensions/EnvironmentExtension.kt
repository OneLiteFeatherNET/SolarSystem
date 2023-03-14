package dev.themeinerlp.solarsystem.bukkit.extensions

import dev.themeinerlp.solarsystem.api.wrapper.world.Environment
import org.bukkit.World

fun Environment.toBukkit(): World.Environment {
    return World.Environment.valueOf(this.bukkitName)
}

fun World.Environment.toSolar(): Environment {
    return Environment.values().first { it.bukkitName == this.name }
}