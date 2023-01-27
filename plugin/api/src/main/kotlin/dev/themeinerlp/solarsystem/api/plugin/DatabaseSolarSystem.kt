package dev.themeinerlp.solarsystem.api.plugin

import com.typesafe.config.ConfigFactory
import com.zaxxer.hikari.HikariDataSource
import dev.themeinerlp.solarsystem.api.config.SQLConfig
import dev.themeinerlp.solarsystem.api.database.PlanetTables
import dev.themeinerlp.solarsystem.api.utils.CONFIG_FILE_NAME
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.hocon.Hocon
import kotlinx.serialization.hocon.decodeFromConfig
import org.bukkit.plugin.java.JavaPlugin
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.statements.StatementContext
import org.jetbrains.exposed.sql.statements.expandArgs
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import java.nio.file.Files
import java.util.logging.Logger
import kotlin.io.path.reader

abstract class DatabaseSolarSystem<T> : JavaPlugin(), SolarSystem<T> {

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

    class SolarSystem2SQLLogger(private val logger: Logger) : SqlLogger {
        override fun log(context: StatementContext, transaction: Transaction) {
            logger.finest("SQL: " + context.expandArgs(transaction))
        }
    }

}