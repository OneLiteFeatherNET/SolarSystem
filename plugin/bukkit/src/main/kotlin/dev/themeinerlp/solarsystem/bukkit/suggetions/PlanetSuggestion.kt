package dev.themeinerlp.solarsystem.bukkit.suggetions

import cloud.commandframework.annotations.suggestions.Suggestions
import cloud.commandframework.context.CommandContext
import dev.themeinerlp.solarsystem.api.database.PlanetEntity
import dev.themeinerlp.solarsystem.bukkit.model.BukkitAsteroid
import org.bukkit.Bukkit
import org.bukkit.util.StringUtil
import org.jetbrains.exposed.sql.transactions.transaction

class PlanetSuggestion {
    @Suggestions(value = "planets")
    fun planetSuggestions(asteroid: CommandContext<BukkitAsteroid>, input: String): List<String> = transaction {
        return@transaction StringUtil.copyPartialMatches(
            input,
            PlanetEntity.all().filter { Bukkit.getWorld(it.name) != null }.map { it.name },
            mutableListOf()
        )
    }
}