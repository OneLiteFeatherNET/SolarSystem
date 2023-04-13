package dev.themeinerlp.solarsystem.api.utils

import net.benwoodworth.knbt.Nbt
import net.benwoodworth.knbt.NbtCompression
import net.benwoodworth.knbt.NbtVariant

object Nbt {
    val nbt by lazy {
        Nbt {
            variant = NbtVariant.Java
            compression = NbtCompression.Zlib
        }
    }
}