package dev.themeinerlp.solarsystem.api.database

import dev.themeinerlp.solarsystem.api.wrapper.world.Environment
import dev.themeinerlp.solarsystem.api.wrapper.world.WorldType
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object PlanetTables : IntIdTable() {
    val name = text("name").index()
    val environment = enumeration<Environment>("environment").default(Environment.NORMAL)
    val worldType = enumeration<WorldType>("worldtype").default(WorldType.NORMAL)
    val seed = long("seed").default(0)
    val generator = text("generator").nullable().index()
    val alias = text("alias").nullable().index()
    val monsterSpawning = bool("monsterSpawning").default(true)
    val animalsSpawning = bool("animalsSpawning").default(true)
    val pvp = bool("pvp").default(true)
    val hidden = bool("hidden").default(false)
    val weather = bool("weather").default(true)
    val hunger = bool("hunger").default(true)
    val autoHeal = bool("autoHeal").default(true)
    val adjustSpawn = bool("adjustSpawn").default(true)
    val autoLoad = bool("autoLoad").default(true)
    val bedRespawn = bool("bedRespawn").default(true)
    val allowFlight = bool("allowFlight").default(false)
    val time = long("time").default(0)
    val playerLimit = integer("playerLimit").default(-1)
}

class PlanetEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<PlanetEntity>(PlanetTables)

    var name by PlanetTables.name
    var environment by PlanetTables.environment
    var worldType by PlanetTables.worldType
    var seed by PlanetTables.seed
    var generator by PlanetTables.generator
    var alias by PlanetTables.alias
    var monsterSpawning by PlanetTables.monsterSpawning
    var animalsSpawning by PlanetTables.animalsSpawning
    var pvp by PlanetTables.pvp
    var hidden by PlanetTables.hidden
    var weather by PlanetTables.weather
    var hunger by PlanetTables.hunger
    var autoHeal by PlanetTables.autoHeal
    var adjustSpawn by PlanetTables.adjustSpawn
    var autoLoad by PlanetTables.autoLoad
    var bedRespawn by PlanetTables.bedRespawn
    var allowFlight by PlanetTables.allowFlight
    var time by PlanetTables.time
    var playerLimit by PlanetTables.playerLimit
    val flags by FlagEntity referrersOn FlagTables.planet
}
