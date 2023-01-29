package dev.themeinerlp.solarsystem.api.commands

import cloud.commandframework.annotations.Argument
import cloud.commandframework.annotations.CommandDescription
import cloud.commandframework.annotations.CommandMethod
import cloud.commandframework.annotations.suggestions.Suggestions
import cloud.commandframework.context.CommandContext
import dev.themeinerlp.solarsystem.api.utils.Asteroid
import org.bukkit.GameRule
import org.bukkit.World
import org.bukkit.util.StringUtil

class GameRuleCommand {

    @CommandMethod("planet gamerule <name> <value>")
    @CommandDescription("Delete a world forever!")
    fun gamerulePlanet(
        asteroid: Asteroid<World>,
        @Argument(value = "name", suggestions = "gamerules")
        gameRule: String,
        @Argument(value = "value")
        value: Any,
    ) {
        /*val world = asteroid.currentPlanet.getOriginWorld() ?: return
        val gameRule = GameRule.getByName(gameRule) ?: return
        world.setGameRule(gameRule, value!!)*/
    }

    @Suggestions(value = "gamerules")
    fun gameRuleSuggestions(asteroid: CommandContext<Asteroid<World>>, input: String): List<String> {
        return StringUtil.copyPartialMatches(input, GameRule.values().map { it.name }, mutableListOf())
    }

}