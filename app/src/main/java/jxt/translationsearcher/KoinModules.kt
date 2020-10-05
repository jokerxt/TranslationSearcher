package jxt.translationsearcher

import jxt.translationsearcher.meaning_impl.di.meaningFeatureModule
import jxt.translationsearcher.network_impl.networkModule
import jxt.translationsearcher.searcher_impl.di.searcherFeatureModule
import org.koin.core.module.Module

internal val koinModules: List<Module>
    get() = appModule + coreModules + featureModules


internal val coreModules: List<Module> = listOf(
    networkModule
)

internal val featureModules = listOf(
    searcherFeatureModule,
    meaningFeatureModule
)