package dev.themeinerlp.solarsystem.api.wrapper.world

enum class WorldType(val bukkitValue: String, val bukkitName: String) {
    NORMAL("DEFAULT", "NORMAL"),
    FLAT("FLAT", "FLAT"),
    LARGE_BIOMES("LARGEBIOMES", "LARGE_BIOMES"),
    AMPLIFIED("AMPLIFIED", "AMPLIFIED")
}
