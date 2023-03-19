package dev.themeinerlp.solarsystem.api.database

import dev.themeinerlp.solarsystem.api.flag.WorldFlag
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object FlagTables : IntIdTable() {
    val flag = enumeration<WorldFlag>("flag").index()
    val value = text("value").nullable()
    val planet = reference("planet", PlanetTables)
}

class FlagEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<FlagEntity>(FlagTables)

    var flag by FlagTables.flag
    var value by FlagTables.value
    var planet by PlanetEntity referencedOn FlagTables.planet
}