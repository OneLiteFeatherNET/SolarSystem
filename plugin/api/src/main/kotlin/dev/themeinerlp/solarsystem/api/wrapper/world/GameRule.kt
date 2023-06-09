package dev.themeinerlp.solarsystem.api.wrapper.world

import kotlin.reflect.KClass

enum class GameRule(
    val bukkitName: String,
    val vanillaName: String,
    val classType: KClass<*>,
) {
    ANNOUNCE_ADVANCEMENTS("ANNOUNCE_ADVANCEMENTS", "announceAdvancements", Boolean::class),
    COMMAND_BLOCK_OUTPUT("COMMAND_BLOCK_OUTPUT", "commandBlockOutput", Boolean::class),
    DISABLE_ELYTRA_MOVEMENT_CHECK("DISABLE_ELYTRA_MOVEMENT_CHECK", "disableElytraMovementCheck", Boolean::class),
    DO_DAYLIGHT_CYCLE("DO_DAYLIGHT_CYCLE", "doDaylightCycle", Boolean::class),
    DO_ENTITY_DROPS("DO_ENTITY_DROPS", "DO_FIRE_TICK", Boolean::class),
    DO_LIMITED_CRAFTING("DO_LIMITED_CRAFTING", "doLimitedCrafting", Boolean::class),
    DO_MOB_LOOT("DO_MOB_LOOT", "doMobLoot", Boolean::class),
    DO_MOB_SPAWNING("DO_MOB_SPAWNING", "doMobSpawning", Boolean::class),
    DO_TILE_DROPS("DO_TILE_DROPS", "doTileDrops", Boolean::class),
    DO_WEATHER_CYCLE("DO_WEATHER_CYCLE", "doWeatherCycle", Boolean::class),
    KEEP_INVENTORY("KEEP_INVENTORY", "keepInventory", Boolean::class),
    LOG_ADMIN_COMMANDS("LOG_ADMIN_COMMANDS", "logAdminCommands", Boolean::class),
    MOB_GRIEFING("MOB_GRIEFING", "mobGriefing", Boolean::class),
    NATURAL_REGENERATION("NATURAL_REGENERATION", "naturalRegeneration", Boolean::class),
    REDUCED_DEBUG_INFO("REDUCED_DEBUG_INFO", "reducedDebugInfo", Boolean::class),
    SEND_COMMAND_FEEDBACK("SEND_COMMAND_FEEDBACK", "sendCommandFeedback", Boolean::class),
    SHOW_DEATH_MESSAGES("SHOW_DEATH_MESSAGES", "showDeathMessages", Boolean::class),
    SPECTATORS_GENERATE_CHUNKS("SPECTATORS_GENERATE_CHUNKS", "spectatorsGenerateChunks", Boolean::class),
    DISABLE_RAIDS("DISABLE_RAIDS", "disableRaids", Boolean::class),
    DO_INSOMNIA("DO_INSOMNIA", "doInsomnia", Boolean::class),
    DO_IMMEDIATE_RESPAWN("DO_IMMEDIATE_RESPAWN", "doImmediateRespawn", Boolean::class),
    DROWNING_DAMAGE("DROWNING_DAMAGE", "drowningDamage", Boolean::class),
    FALL_DAMAGE("FALL_DAMAGE", "fallDamage", Boolean::class),
    FIRE_DAMAGE("FIRE_DAMAGE", "fireDamage", Boolean::class),
    FREEZE_DAMAGE("FREEZE_DAMAGE", "freezeDamage", Boolean::class),
    DO_TRADER_SPAWNING("DO_TRADER_SPAWNING", "doTraderSpawning", Boolean::class),
    DO_WARDEN_SPAWNING("DO_WARDEN_SPAWNING", "doWardenSpawning", Boolean::class),
    FORGIVE_DEAD_PLAYERS("FORGIVE_DEAD_PLAYERS", "forgiveDeadPlayers", Boolean::class),
    UNIVERSAL_ANGER("UNIVERSAL_ANGER", "universalAnger", Boolean::class),
    BLOCK_EXPLOSION_DROP_DECAY("BLOCK_EXPLOSION_DROP_DECAY", "blockExplosionDropDecay", Boolean::class),
    MOB_EXPLOSION_DROP_DECAY("MOB_EXPLOSION_DROP_DECAY", "mobExplosionDropDecay", Boolean::class),
    TNT_EXPLOSION_DROP_DECAY("TNT_EXPLOSION_DROP_DECAY", "tntExplosionDropDecay", Boolean::class),
    WATER_SOURCE_CONVERSION("WATER_SOURCE_CONVERSION", "waterSourceConversion", Boolean::class),
    LAVA_SOURCE_CONVERSION("LAVA_SOURCE_CONVERSION", "lavaSourceConversion", Boolean::class),
    GLOBAL_SOUND_EVENTS("GLOBAL_SOUND_EVENTS", "globalSoundEvents", Boolean::class),
    RANDOM_TICK_SPEED("RANDOM_TICK_SPEED", "randomTickSpeed", Int::class),
    SPAWN_RADIUS("SPAWN_RADIUS", "spawnRadius", Int::class),
    MAX_ENTITY_CRAMMING("MAX_ENTITY_CRAMMING", "maxEntityCramming", Int::class),
    MAX_COMMAND_CHAIN_LENGTH("MAX_COMMAND_CHAIN_LENGTH", "maxCommandChainLength", Int::class),
    PLAYERS_SLEEPING_PERCENTAGE("PLAYERS_SLEEPING_PERCENTAGE", "playersSleepingPercentage", Int::class),
    SNOW_ACCUMULATION_HEIGHT("SNOW_ACCUMULATION_HEIGHT", "snowAccumulationHeight", Int::class)
}