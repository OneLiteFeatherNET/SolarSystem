package dev.themeinerlp.solarsystem.api.plugin

import com.zaxxer.hikari.HikariDataSource
import dev.themeinerlp.solarsystem.api.config.SQLConfig
import dev.themeinerlp.solarsystem.api.database.PlanetEntity
import dev.themeinerlp.solarsystem.api.database.PlanetTables
import dev.themeinerlp.solarsystem.api.service.SolarService
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

class DatabaseSolarSystem<T>(
    private val config: SQLConfig,
    private val logger: Logger,
    override val solarService: SolarService<T>,
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
            solarService.loadPlanetByName(it.name)
        }
    }

    class SolarSystem2SQLLogger(private val logger: Logger) : SqlLogger {
        override fun log(context: StatementContext, transaction: Transaction) {
            logger.finest("SQL: " + context.expandArgs(transaction))
        }
    }


}