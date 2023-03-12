package dev.themeinerlp.solarsystem.api.wrapper.player

enum class GameMode(
    val bukkitValue: Int,
    val bukkitName: String,
) {
    SURVIVAL(0, "SURVIVAL"),
    CREATIVE(1, "CREATIVE"),
    ADVENTURE(2, "ADVENTURE"),
    SPECTATOR(3, "SPECTATOR")
}
