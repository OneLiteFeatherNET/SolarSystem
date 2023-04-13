package dev.themeinerlp.solarsystem.api.flag

class FlagParseException(flag: WorldFlag<*,*,*>,
                         value: String,
                         errorMessage: String,
                         vararg args: String) : Exception(String.format("Failed to parse flag of type '%s'. Value '%s' was not accepted.", flag.flagName, value)) {
}