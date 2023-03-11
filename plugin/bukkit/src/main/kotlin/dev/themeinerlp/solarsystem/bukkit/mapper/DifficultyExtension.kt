package dev.themeinerlp.solarsystem.bukkit.mapper

import dev.themeinerlp.solarsystem.api.wrapper.world.Difficulty

fun Difficulty.toBukkit(): org.bukkit.Difficulty {
    return org.bukkit.Difficulty.valueOf(this.name)
}

fun org.bukkit.Difficulty.toSolar(): Difficulty {
    return Difficulty.valueOf(this.name)
}