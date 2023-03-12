package dev.themeinerlp.solarsystem.bukkit.commands

import cloud.commandframework.annotations.Argument
import cloud.commandframework.annotations.CommandDescription
import cloud.commandframework.annotations.CommandMethod
import cloud.commandframework.annotations.CommandPermission
import cloud.commandframework.annotations.Flag
import dev.themeinerlp.solarsystem.api.world.Planet
import dev.themeinerlp.solarsystem.api.wrapper.world.WorldType
import dev.themeinerlp.solarsystem.bukkit.model.BukkitAsteroid
import dev.themeinerlp.solarsystem.bukkit.utils.COMMANDS_CREATE
import org.bukkit.World.Environment

class CreateCommand {

    @CommandMethod("planet create <name> <env>")
    @CommandPermission(COMMANDS_CREATE)
    @CommandDescription("")
    fun create(
        asteroid: BukkitAsteroid,
        @Argument("name") name: String,
        @Argument("env") env: Environment,
        @Flag("s") seed: Long?,
        @Flag("g") generator: String?,
        @Flag("t") worldType: WorldType?,
        @Flag("a") structure: Boolean = true,
        @Flag("n") adjustSpawn: Boolean = true,
    ) {
        val builder = Planet.Builder().name(name).environment(env)
        if (seed != null) {
            builder.seed(seed)
        }
        if (generator != null) {
            builder.generator(generator)
        }
        if (worldType != null) {
            builder.worldType(worldType)
        }
        if (structure) {
            builder.generateStructures(structure)
        }
        if (adjustSpawn) {
            builder.useSpawnAdjust(adjustSpawn)
        }
        asteroid.service.createPlanet(builder)
    }

}
