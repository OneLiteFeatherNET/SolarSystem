package dev.themeinerlp.solarsystem.api.wrapper.player

enum class GameMode(
    val bukkitValue: Int,
) {
    SURVIVAL(0),
    CREATIVE(1),
    ADVENTURE(2),
    SPECTATOR(3)
}
