rootProject.name = "Translation Searcher"

// Common
include(
    ":app",
    ":common:android",
    ":common:androidutil",
    ":common:kotlin"
)

// Core
include(
    ":app",
    ":core:navigation:impl",
    ":core:navigation:api",
    ":core:network:impl",
    ":core:network:api",
    ":core:database:impl",
    ":core:database:api"
)

// Features
include(
    ":app",
    ":feature:searcher:impl",
    ":feature:searcher:api",
    ":feature:meaning:impl",
    ":feature:meaning:api"
)