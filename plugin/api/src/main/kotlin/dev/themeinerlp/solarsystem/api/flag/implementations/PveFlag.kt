package dev.themeinerlp.solarsystem.api.flag.implementations
class PveFlag(value: Boolean) : BooleanFlag<PveFlag>(value) {

    companion object {
        val PVE_FLAG_TRUE = PveFlag(true)
        val PVE_FLAG_FALSE = PveFlag(false)
    }
    override fun flagOf(value: Boolean): PveFlag {
        return if(value) {
            PVE_FLAG_TRUE
        } else {
            PVE_FLAG_FALSE
        }
    }
}