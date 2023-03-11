package dev.themeinerlp.solarsystem.example.kotlin

import dev.themeinerlp.solarsystem.api.plugin.SolarSystem
import org.bukkit.plugin.java.JavaPlugin

class ExamplePlugin : JavaPlugin() {

    override fun onEnable() {
        if (server.pluginManager.isPluginEnabled("SolarSystem")) {
            val solarSystemPlugin = server.pluginManager.getPlugin("SolarSystem") as SolarSystem<*>
            val planets = solarSystemPlugin.solarService.getPlanets()
            planets.forEach {
                // Prints from everyworld the name
                println(it.name)
            }
        }
    }
}