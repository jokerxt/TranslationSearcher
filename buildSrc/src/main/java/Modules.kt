@file:Suppress("unused", "SpellCheckingInspection")

object CommonModule {
    const val app = ":app"

    const val android = ":common:android"
    const val androidUtil = ":common:androidutil"
    const val kotlin = ":common:kotlin"
}

object CoreModule {
    const val navigationApi = ":core:navigation:api"
    const val navigationImpl = ":core:navigation:impl"
    
    const val networkApi = ":core:network:api"
    const val networkImpl = ":core:network:impl"
    
    const val databaseApi = ":core:database:api"
    const val databaseImpl = ":core:database:impl"
}

object FeatureModule {
    const val searcherApi = ":feature:searcher:api"
    const val searcherImpl = ":feature:searcher:impl"
    
    const val meaningApi = ":feature:meaning:api"
    const val meaningImpl = ":feature:meaning:impl"
}