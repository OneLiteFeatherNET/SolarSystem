package dev.themeinerlp.solarsystem.bukkit.mapper

import dev.themeinerlp.solarsystem.api.wrapper.world.WorldType

fun WorldType.toBukkit(): org.bukkit.WorldType {
    return org.bukkit.WorldType.valueOf(this.bukkitValue)
}

fun org.bukkit.WorldType.toSolar(): WorldType {
    return WorldType.values().first { it.bukkitValue == this.getName() }
}