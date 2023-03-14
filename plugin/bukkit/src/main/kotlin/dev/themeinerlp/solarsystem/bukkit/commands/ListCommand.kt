package dev.themeinerlp.solarsystem.bukkit.commands

import cloud.commandframework.annotations.CommandMethod
import cloud.commandframework.annotations.CommandPermission
import dev.themeinerlp.solarsystem.bukkit.model.BukkitAsteroid
import dev.themeinerlp.solarsystem.bukkit.utils.COMMANDS_LIST
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.Bukkit

class ListCommand {

    private val miniMessage = { s: String -> MiniMessage.miniMessage().deserialize(s) }

    @CommandMethod("planet list")
    @CommandPermission(COMMANDS_LIST)
    fun list(asteroid: BukkitAsteroid) {
        val messages = asteroid.service.getPlanets().map {
            val loaded = Bukkit.getWorld(it.name) != null
            if (loaded) {
                miniMessage("\u22A2 <green><click:SUGGEST_COMMAND:/planet teleport ${it.name}>${it.name}</click>")
            } else {
                miniMessage("\u22A2 <i><green><click:SUGGEST_COMMAND:/planet import ${it.name}>${it.name}</click>")
            }
        }
        asteroid.sender.sendMessage("\u2308 Planets: ")
        messages.forEach { asteroid.sender.sendMessage(it) }
    }
}
