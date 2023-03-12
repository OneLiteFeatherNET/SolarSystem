package dev.themeinerlp.solarsystem.bukkit.parser

import cloud.commandframework.annotations.parsers.Parser
import cloud.commandframework.context.CommandContext
import dev.themeinerlp.solarsystem.bukkit.model.BukkitAsteroid
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class PlanetParser {

    @Parser(name = "planet", suggestions = "planets")
    fun planetParser(asteroid: CommandContext<BukkitAsteroid>, input: Queue<String>) = transaction {
        val name = input.remove()
        return@transaction asteroid.sender.service.getPlanetByName(name)
    }


}