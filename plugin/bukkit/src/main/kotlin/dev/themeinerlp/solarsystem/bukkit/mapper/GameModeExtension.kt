package dev.themeinerlp.solarsystem.bukkit.mapper

import dev.themeinerlp.solarsystem.api.wrapper.player.GameMode

fun GameMode.toBukkit(): org.bukkit.GameMode {
    return org.bukkit.GameMode.valueOf(this.name)
}

fun org.bukkit.GameMode.toSolar(): GameMode {
    return GameMode.valueOf(this.name)
}