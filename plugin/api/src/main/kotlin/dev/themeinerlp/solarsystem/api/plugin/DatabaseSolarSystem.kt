package dev.themeinerlp.solarsystem.api.plugin

import com.zaxxer.hikari.HikariDataSource
import dev.themeinerlp.solarsystem.api.config.SQLConfig
import dev.themeinerlp.solarsystem.api.database.PlanetEntity
import dev.themeinerlp.solarsystem.api.database.PlanetTables
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.DatabaseConfig
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.SqlLogger
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.statements.StatementContext
import org.jetbrains.exposed.sql.statements.expandArgs
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.logging.Logger

abstract class DatabaseSolarSystem<T>(
    val config: SQLConfig,
    val logger: Logger,
) : SolarSystem<T> {

    fun connect() {
        val db = Database.connect(
            HikariDataSource(config.toHikariConfig()),
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

    fun autoLoadPlanets() = transaction {
        PlanetEntity.find { PlanetTables.autoLoad eq true }.forEach {
            getJavaSolarService().loadPlanetByName(it.name)
        }
    }

    class SolarSystem2SQLLogger(private val logger: Logger) : SqlLogger {
        override fun log(context: StatementContext, transaction: Transaction) {
            logger.finest("SQL: " + context.expandArgs(transaction))
        }
    }


}