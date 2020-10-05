@file:Suppress("MemberVisibilityCanBePrivate", "unused", "SpellCheckingInspection")

import org.gradle.api.JavaVersion

object App {
    const val id = "jxt.translationsearcher"
    const val appName = "Translation Searcher"
    
    const val debugSuffix = ".debug"
}

object Release {
    const val versionCode = 1
    const val versionName = "0.01"
}

object Version {
    // Language
    val javaVersion = JavaVersion.VERSION_1_8
    val javaVersionString = javaVersion.toString()
    const val kotlinVersion = "1.4.10"
    const val gradleVersion = "4.0.1"

    // SDK
    const val minVersion = 21
    const val targetVersion = 30
    const val compileVersion = 30
}