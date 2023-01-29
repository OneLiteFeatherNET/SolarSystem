package dev.themeinerlp.solarsystem.api.parser

import cloud.commandframework.annotations.parsers.Parser
import cloud.commandframework.annotations.suggestions.Suggestions
import cloud.commandframework.context.CommandContext
import dev.themeinerlp.solarsystem.api.database.PlanetEntity
import dev.themeinerlp.solarsystem.api.utils.Asteroid
import org.bukkit.World
import org.bukkit.util.StringUtil
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.Queue

class PlanetParser {

    @Parser(name = "planet", suggestions = "planets")
    fun planetParser(asteroid: CommandContext<Asteroid<World>>, input: Queue<String>) = transaction {
        val name = input.remove()
        return@transaction asteroid.sender.service.getPlanetByName(name)
    }


}