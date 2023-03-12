package dev.themeinerlp.solarsystem.bukkit.commands

import cloud.commandframework.annotations.Argument
import cloud.commandframework.annotations.CommandDescription
import cloud.commandframework.annotations.CommandMethod
import cloud.commandframework.annotations.CommandPermission
import dev.themeinerlp.solarsystem.api.world.Planet
import dev.themeinerlp.solarsystem.api.wrapper.world.GameRule
import dev.themeinerlp.solarsystem.bukkit.model.BukkitAsteroid
import dev.themeinerlp.solarsystem.bukkit.utils.COMMANDS_DELETE
import org.bukkit.World

class GameRuleCommand {
    @CommandMethod("planet gamerule <name> <gamerule> <value>")
    @CommandDescription("Change gamerule of a world")
    @CommandPermission(COMMANDS_DELETE)
    fun gameRule(
        asteroid: BukkitAsteroid,
        @Argument(
            value = "name",
            parserName = "planet"
        )
        planet: Planet<World>,
        @Argument(
            value = "gamerule",
        )
        rule: GameRule,
        @Argument(
            value = "value",
            suggestions = "gamerule"
        )
        value: String,
    ) {
        val finalValue = when (
            rule.classType
        ) {
            Boolean::class -> value.toBoolean()
            Int::class -> value.toInt()
            else -> return
        }
        asteroid.service.changeGameRule(planet, rule, finalValue)
    }
}