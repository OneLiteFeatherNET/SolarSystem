package dev.themeinerlp.solarsystem.bukkit

import cloud.commandframework.annotations.AnnotationParser
import cloud.commandframework.arguments.parser.ParserParameters
import cloud.commandframework.arguments.parser.StandardParameters
import cloud.commandframework.bukkit.CloudBukkitCapabilities
import cloud.commandframework.execution.CommandExecutionCoordinator
import cloud.commandframework.meta.CommandMeta
import cloud.commandframework.minecraft.extras.MinecraftHelp
import cloud.commandframework.paper.PaperCommandManager
import com.typesafe.config.ConfigFactory
import dev.themeinerlp.solarsystem.api.config.SQLConfig
import dev.themeinerlp.solarsystem.api.plugin.DatabaseSolarSystem
import dev.themeinerlp.solarsystem.api.plugin.SolarSystem
import dev.themeinerlp.solarsystem.api.service.SolarService
import dev.themeinerlp.solarsystem.api.utils.CONFIG_FILE_NAME
import dev.themeinerlp.solarsystem.bukkit.commands.CreateCommand
import dev.themeinerlp.solarsystem.bukkit.commands.DeleteCommand
import dev.themeinerlp.solarsystem.bukkit.commands.HelpCommand
import dev.themeinerlp.solarsystem.bukkit.commands.ImportCommand
import dev.themeinerlp.solarsystem.bukkit.commands.ListCommand
import dev.themeinerlp.solarsystem.bukkit.commands.LoadCommand
import dev.themeinerlp.solarsystem.bukkit.commands.RemoveCommand
import dev.themeinerlp.solarsystem.bukkit.commands.TeleportCommand
import dev.themeinerlp.solarsystem.bukkit.commands.UnloadCommand
import dev.themeinerlp.solarsystem.bukkit.model.BukkitAsteroid
import dev.themeinerlp.solarsystem.bukkit.parser.PlanetParser
import dev.themeinerlp.solarsystem.bukkit.service.BukkitSolarService
import dev.themeinerlp.solarsystem.bukkit.suggetions.PlanetSuggestion
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.hocon.Hocon
import kotlinx.serialization.hocon.decodeFromConfig
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor
import org.bukkit.World
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.nio.file.Files
import java.util.function.Function
import kotlin.io.path.reader

class BukkitSolar : JavaPlugin(), SolarSystem<World> {

    lateinit var minecraftHelp: MinecraftHelp<BukkitAsteroid>
    private lateinit var localSolarService: SolarService<World>
    private lateinit var solarSystem: SolarSystem<World>

    private var paperCommandManager: PaperCommandManager<BukkitAsteroid>? = null
    private var annotationParser: AnnotationParser<BukkitAsteroid>? = null
    override fun onEnable() {
        this.localSolarService = BukkitSolarService()
        this.solarSystem = DatabaseSolarSystem(readConfig(), logger, this.localSolarService)
        if (this.solarSystem is DatabaseSolarSystem) {
            (this.solarSystem as DatabaseSolarSystem<World>).connect()
            (this.solarSystem as DatabaseSolarSystem<World>).autoLoadPlanets()
        }
        createCommandSystem()
        registerCommands()
        createHelpSystem()
    }

    override fun onDisable() {
        super.onDisable()
    }

    private fun createHelpSystem() {
        if (paperCommandManager != null) {
            minecraftHelp = MinecraftHelp(
                "/planet help",
                {
                    it.sender
                },
                paperCommandManager!!
            )
            minecraftHelp.helpColors = MinecraftHelp.HelpColors.of(
                TextColor.color(0, 217, 147),
                TextColor.color(9, 55, 255),
                NamedTextColor.AQUA,
                NamedTextColor.GRAY,
                NamedTextColor.DARK_GRAY
            )
        }

    }

    private fun createCommandSystem() {
        paperCommandManager = PaperCommandManager(
            this,
            CommandExecutionCoordinator.simpleCoordinator(),
            {
                BukkitAsteroid(entity = it as Player, service = this.solarService, sender = it)
            },
            {
                it.sender
            }
        )
        if (paperCommandManager!!.hasCapability(CloudBukkitCapabilities.BRIGADIER)) {
            paperCommandManager!!.registerBrigadier()
            logger.info("Brigadier support enabled")
        }
        if (paperCommandManager!!.hasCapability(CloudBukkitCapabilities.ASYNCHRONOUS_COMPLETION)) {
            paperCommandManager!!.registerAsynchronousCompletions()
            logger.info("Asynchronous completions enabled")
        }
        val commandMetaFunction = Function<ParserParameters, CommandMeta> { p: ParserParameters ->
            CommandMeta.simple().with(
                CommandMeta.DESCRIPTION,
                p.get(StandardParameters.DESCRIPTION, "No description")
            ).build()
        }

        annotationParser = AnnotationParser(
            paperCommandManager!!,
            BukkitAsteroid::class.java,
            commandMetaFunction
        )
    }

    @OptIn(ExperimentalSerializationApi::class)
    fun readConfig(): SQLConfig {
        val sqlConfigFile = dataFolder.toPath().resolve(CONFIG_FILE_NAME)
        if (!Files.exists(sqlConfigFile)) {
            saveResource(CONFIG_FILE_NAME, true)
        }
        sqlConfigFile.reader().use {
            val config = ConfigFactory.parseReader(it)
            return Hocon.decodeFromConfig(config)
        }
    }

    private fun registerCommands() {
        if (annotationParser != null) {
            annotationParser!!.parse(PlanetSuggestion())
            annotationParser!!.parse(PlanetParser())

            annotationParser!!.parse(TeleportCommand())
            annotationParser!!.parse(CreateCommand())
            annotationParser!!.parse(DeleteCommand())
            annotationParser!!.parse(ImportCommand())
            annotationParser!!.parse(ListCommand())
            annotationParser!!.parse(UnloadCommand())
            annotationParser!!.parse(LoadCommand())
            annotationParser!!.parse(RemoveCommand())
            annotationParser!!.parse(HelpCommand(this))
        }
    }

    override val solarService: SolarService<World>
        get() = this.localSolarService


}
