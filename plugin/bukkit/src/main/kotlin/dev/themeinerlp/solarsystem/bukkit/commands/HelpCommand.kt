package dev.themeinerlp.solarsystem.bukkit.commands

import cloud.commandframework.annotations.Argument
import cloud.commandframework.annotations.CommandDescription
import cloud.commandframework.annotations.CommandMethod
import cloud.commandframework.annotations.specifier.Greedy
import dev.themeinerlp.solarsystem.api.utils.Asteroid
import dev.themeinerlp.solarsystem.bukkit.BukkitSolar
import org.bukkit.World

class HelpCommand(val bukkitSolar: BukkitSolar) {
    @CommandDescription("Shows the help menu")
    @CommandMethod("planet help [query]")
    fun onHelp(asteroid: Asteroid<World>, @Argument("query") @Greedy query: String?) {
        println("Test")
        bukkitSolar.minecraftHelp.queryCommands(query ?: "", asteroid)
    }
}
