package dev.themeinerlp.solarsystem.bukkit.extensions

import dev.themeinerlp.solarsystem.api.wrapper.player.GameMode

fun GameMode.toBukkit(): org.bukkit.GameMode {
    return org.bukkit.GameMode.valueOf(this.bukkitName)
}

fun org.bukkit.GameMode.toSolar(): GameMode {
    return GameMode.values().first { it.bukkitName == this.name }
}