package dev.themeinerlp.solarsystem.api.wrapper.world

enum class Difficulty(
    val bukkitValue: Int,
    val bukkitName: String,
) {
    PEACEFUL(0, "PEACEFUL"),
    EASY(1, "EASY"),
    NORMAL(2, "NORMAL"),
    HARD(3, "HARD");
}
