package dev.themeinerlp.solarsystem.api.database

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object PlanetTables: IntIdTable() {
    val name = text("name").index()
    val difficulty = integer("difficulty")
    val environment = integer("environment")
    val worldType = varchar("worldtype", 255).index()
    val seed = long("seed").default(0)
    val generator = text("generator").nullable().index()
    val alias = text("alias").nullable().index()
    val monsterSpawning = bool("monsterSpawning")
    val pvp = bool("pvp").default(true)
    val hidden = bool("hidden").default(false)
    val weather = bool("weather").default(true)
    val hunger = bool("hunger").default(true)
    val autoHeal = bool("autoHeal")
    val adjustSpawn = bool("adjustSpawn")
    val autoLoad = bool("autoLoad")
    val bedRespawn = bool("bedRespawn")
    val allowFlight = bool("allowFlight")
    val time = long("time")
    val playerLimit = long("playerLimit")
    val respawnPlanet = reference("respawnPlanet", PlanetTables).nullable()
    val gamemode = integer("gamemode")
}

class PlantEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<PlantEntity>(PlanetTables)
    val name by PlanetTables.name
    val difficulty by PlanetTables.difficulty
    val environment by PlanetTables.environment
    val worldType by PlanetTables.worldType
    val seed by PlanetTables.seed
    val generator by PlanetTables.generator
    val alias by PlanetTables.alias
    val monsterSpawning by PlanetTables.monsterSpawning
    val pvp by PlanetTables.pvp
    val hidden by PlanetTables.hidden
    val weather by PlanetTables.weather
    val hunger by PlanetTables.hunger
    val autoHeal by PlanetTables.autoHeal
    val adjustSpawn by PlanetTables.adjustSpawn
    val autoLoad by PlanetTables.autoLoad
    val bedRespawn by PlanetTables.bedRespawn
    val allowFlight by PlanetTables.allowFlight
    val time by PlanetTables.time
    val playerLimit by PlanetTables.playerLimit
    val respawnPlanet by PlantEntity optionalReferrersOn PlanetTables.respawnPlanet
    val gamemode by PlanetTables.gamemode
}
