package dev.themeinerlp.solarsystem.bukkit.suggetions

import cloud.commandframework.annotations.suggestions.Suggestions
import cloud.commandframework.context.CommandContext
import dev.themeinerlp.solarsystem.api.world.Planet
import dev.themeinerlp.solarsystem.api.wrapper.world.GameRule
import dev.themeinerlp.solarsystem.bukkit.model.BukkitAsteroid
import org.bukkit.World
import org.bukkit.util.StringUtil

class GameRuleSuggestion {
    @Suggestions(value = "gamerule")
    fun planetSuggestions(asteroid: CommandContext<BukkitAsteroid>, input: String): List<String> {
        val solarGameRule = asteroid.get<GameRule>("gamerule")
        val rule =
            org.bukkit.GameRule.getByName(solarGameRule.vanillaName) as org.bukkit.GameRule<Any>
        return if (solarGameRule.classType == Int::class) {
            val world = asteroid.get<Planet<World>>("name")
            val value = world.getOriginWorld()?.getGameRuleValue<Any>(rule)
            StringUtil.copyPartialMatches(
                input,
                listOf(value?.toString()),
                mutableListOf()
            )
        } else {
            StringUtil.copyPartialMatches(
                input,
                listOf("true", "false"),
                mutableListOf()
            )
        }

    }
}