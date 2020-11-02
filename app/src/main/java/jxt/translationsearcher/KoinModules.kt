package jxt.translationsearcher

import jxt.translationsearcher.network_impl.networkModule
import org.koin.core.module.Module

internal val koinModules: List<Module>
    get() = appModule + coreModules

internal val coreModules: List<Module> = listOf(
    networkModule
)