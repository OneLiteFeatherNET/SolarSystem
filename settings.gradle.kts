
rootProject.name = "Solarsystem"

include("plugin:bukkit")
findProject(":plugin:bukkit")?.name = "bukkit"


include("example:java")
findProject(":example:java")?.name = "java"

include("example:kotlin")
findProject(":example:kotlin")?.name = "kotlin"
include("plugin:bukkit")
findProject(":plugin:bukkit")?.name = "bukkit"
include("plugin:api")
findProject(":plugin:api")?.name = "api"
