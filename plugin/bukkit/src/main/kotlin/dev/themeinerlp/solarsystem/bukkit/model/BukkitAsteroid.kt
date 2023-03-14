package dev.themeinerlp.solarsystem.bukkit.model

import dev.themeinerlp.solarsystem.api.service.SolarService
import dev.themeinerlp.solarsystem.api.utils.Asteroid
import org.bukkit.World
import org.bukkit.command.CommandSender

class BukkitAsteroid(sender: CommandSender, service: SolarService<World>) :
    Asteroid<World, CommandSender>(
        sender,
        service
    )