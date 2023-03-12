package dev.themeinerlp.solarsystem.bukkit.extensions

import dev.themeinerlp.solarsystem.api.wrapper.world.Difficulty

fun Difficulty.toBukkit(): org.bukkit.Difficulty {
    return org.bukkit.Difficulty.valueOf(this.bukkitName)
}

fun org.bukkit.Difficulty.toSolar(): Difficulty {
    return Difficulty.values().first { it.bukkitName == this.name }
}