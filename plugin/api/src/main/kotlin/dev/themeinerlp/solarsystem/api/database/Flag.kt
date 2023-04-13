package dev.themeinerlp.solarsystem.api.database

import dev.themeinerlp.solarsystem.api.utils.Nbt
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.encodeToByteArray
import net.benwoodworth.knbt.NbtTag
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object FlagTables : IntIdTable() {
    val flag = text("name").index()
    val value = binary("value").nullable()
    val planet = reference("planet", PlanetTables)
}

class FlagEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<FlagEntity>(FlagTables)

    var flag by FlagTables.flag
    var value: NbtTag by FlagTables.value.transform({ Nbt.nbt.encodeToByteArray(it) },
        { Nbt.nbt.decodeFromByteArray(it ?: ByteArray(0)) })
    var planet by PlanetEntity referencedOn FlagTables.planet
}