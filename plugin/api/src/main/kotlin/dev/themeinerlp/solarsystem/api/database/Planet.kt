package dev.themeinerlp.solarsystem.api.database

import dev.themeinerlp.solarsystem.api.wrapper.player.GameMode
import dev.themeinerlp.solarsystem.api.wrapper.world.Difficulty
import dev.themeinerlp.solarsystem.api.wrapper.world.Environment
import dev.themeinerlp.solarsystem.api.wrapper.world.WorldType
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object PlanetTables : IntIdTable() {
    val name = text("name").index()
    val difficulty = enumeration<Difficulty>("difficulty").default(Difficulty.EASY)
    val environment = enumeration<Environment>("environment").default(Environment.NORMAL)
    val worldType = enumeration<WorldType>("worldtype").default(WorldType.NORMAL)
    val seed = long("seed").default(0)
    val generator = text("generator").nullable().index()
    val alias = text("alias").nullable().index()
    val monsterSpawning = bool("monsterSpawning").default(true)
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
    val respawnPlanet = reference("respawnPlanet", PlanetTables).nullable()
    val gamemode = enumeration<GameMode>("gamemode").default(GameMode.SURVIVAL)
}

class PlanetEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<PlanetEntity>(PlanetTables)

    var name by PlanetTables.name
    var difficulty by PlanetTables.difficulty
    var environment by PlanetTables.environment
    var worldType by PlanetTables.worldType
    var seed by PlanetTables.seed
    var generator by PlanetTables.generator
    var alias by PlanetTables.alias
    var monsterSpawning by PlanetTables.monsterSpawning
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
    val respawnPlanet by PlanetEntity optionalReferrersOn PlanetTables.respawnPlanet
    var gamemode by PlanetTables.gamemode
}
