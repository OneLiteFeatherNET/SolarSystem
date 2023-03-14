package dev.themeinerlp.solarsystem.api.utils

import dev.themeinerlp.solarsystem.api.service.SolarService
import net.kyori.adventure.audience.Audience

open class Asteroid<T, S>(
    val sender: S,
    val service: SolarService<T>,
) : Audience