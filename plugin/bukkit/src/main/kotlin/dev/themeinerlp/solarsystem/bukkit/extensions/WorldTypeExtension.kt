package dev.themeinerlp.solarsystem.bukkit.extensions

import dev.themeinerlp.solarsystem.api.wrapper.world.WorldType

fun WorldType.toBukkit(): org.bukkit.WorldType {
    return org.bukkit.WorldType.valueOf(this.bukkitName)
}

fun org.bukkit.WorldType.toSolar(): WorldType {
    return WorldType.values().first { it.bukkitName == this.name }
}
