package dev.themeinerlp.solarsystem.api.plugin

import cloud.commandframework.annotations.AnnotationParser
import cloud.commandframework.arguments.parser.ParserParameters
import cloud.commandframework.arguments.parser.StandardParameters
import cloud.commandframework.bukkit.CloudBukkitCapabilities
import cloud.commandframework.execution.CommandExecutionCoordinator
import cloud.commandframework.meta.CommandMeta
import cloud.commandframework.paper.PaperCommandManager
import com.typesafe.config.ConfigFactory
import com.zaxxer.hikari.HikariDataSource
import dev.themeinerlp.solarsystem.api.commands.*
import dev.themeinerlp.solarsystem.api.config.SQLConfig
import dev.themeinerlp.solarsystem.api.database.PlanetTables
import dev.themeinerlp.solarsystem.api.database.PlanetEntity
import dev.themeinerlp.solarsystem.api.parser.PlanetParser
import dev.themeinerlp.solarsystem.api.suggetions.PlantSuggestion
import dev.themeinerlp.solarsystem.api.utils.Asteroid
import dev.themeinerlp.solarsystem.api.utils.CONFIG_FILE_NAME
import io.leangen.geantyref.TypeToken
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.hocon.Hocon
import kotlinx.serialization.hocon.decodeFromConfig
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.jetbrains.exposed.sql.SqlLogger
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.DatabaseConfig
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.statements.StatementContext
import org.jetbrains.exposed.sql.statements.expandArgs
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import java.lang.IllegalArgumentException
import java.nio.file.Files
import java.util.function.Function
import java.util.logging.Logger
import kotlin.io.path.reader

abstract class DatabaseSolarSystem<T> : JavaPlugin(), SolarSystem<T> {

    private var paperCommandManager: PaperCommandManager<Asteroid<T>>? = null
    private var annotationParser: AnnotationParser<Asteroid<T>>? = null

    protected fun createCommandSystem() {
        paperCommandManager = PaperCommandManager(
            this,
            CommandExecutionCoordinator.simpleCoordinator(),
            {
                Asteroid(player = it as Player, service = getSolarService(), commandSender = it)
            },
            {
                it.commandSender
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
        val type = object : TypeToken<Asteroid<T>>() {}
        annotationParser = AnnotationParser(
            paperCommandManager!!,
            type,
            commandMetaFunction
        )
    }

    protected fun registerCommands() {
        if (annotationParser != null) {
            annotationParser!!.parse(PlantSuggestion())
            annotationParser!!.parse(PlanetParser())

            annotationParser!!.parse(TeleportCommand())
            annotationParser!!.parse(CreateCommand())
            annotationParser!!.parse(DeleteCommand())
            annotationParser!!.parse(ImportCommand())
            annotationParser!!.parse(UnloadCommand())
            annotationParser!!.parse(LoadCommand())
            annotationParser!!.parse(RemoveCommand())
            // annotationParser!!.parse(GameRuleCommand())
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    protected fun readConfig(): SQLConfig {
        val sqlConfigFile = dataFolder.toPath().resolve(CONFIG_FILE_NAME)
        if (!Files.exists(sqlConfigFile)) {
            saveResource(CONFIG_FILE_NAME, true)
        }
        sqlConfigFile.reader().use {
            val config = ConfigFactory.parseReader(it)
            return Hocon.decodeFromConfig(config)
        }
    }

    protected fun connect(sqlConfig: SQLConfig, logger: Logger) {
        val db = Database.connect(
            HikariDataSource(sqlConfig.toHikariConfig()),
            databaseConfig = DatabaseConfig {
                sqlLogger = SolarSystem2SQLLogger(logger)
            }
        )
        TransactionManager.defaultDatabase = db
        transaction {
            SchemaUtils.createMissingTablesAndColumns(
                PlanetTables
            )
        }
    }

    protected fun autoLoadPlanets() = transaction {
        PlanetEntity.find { PlanetTables.autoLoad eq true }.forEach {
            getSolarService().loadPlanetByName(it.name)
        }
    }

    class SolarSystem2SQLLogger(private val logger: Logger) : SqlLogger {
        override fun log(context: StatementContext, transaction: Transaction) {
            logger.finest("SQL: " + context.expandArgs(transaction))
        }
    }

}