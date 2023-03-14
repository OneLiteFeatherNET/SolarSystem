package dev.themeinerlp.solarsystem.api.wrapper.world

enum class Environment(val bukkitValue: Int, val bukkitName: String) {
    NORMAL(0, "NORMAL"),
    NETHER(-1, "NETHER"),
    THE_END(1, "THE_END"),
    CUSTOM(-999, "CUSTOM")
}