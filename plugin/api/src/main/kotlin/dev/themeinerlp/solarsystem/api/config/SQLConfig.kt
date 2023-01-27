package dev.themeinerlp.solarsystem.api.config

import com.zaxxer.hikari.HikariConfig
import kotlinx.serialization.Serializable

@Serializable
data class SQLConfig(
    val jdbcUrl: String,
    val driverClassName: String,
    val username: String?,
    val password: String?,
    val maximumPoolSize: Int = 10,
) {
    fun toHikariConfig(): HikariConfig {
        return HikariConfig().apply {
            this.jdbcUrl = this@SQLConfig.jdbcUrl
            this.driverClassName = this@SQLConfig.driverClassName
            this.username = this@SQLConfig.username
            this.password = this@SQLConfig.password
            this.maximumPoolSize = this@SQLConfig.maximumPoolSize
        }
    }
}

