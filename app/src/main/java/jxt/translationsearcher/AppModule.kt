package jxt.translationsearcher

import org.koin.dsl.module

internal val appModule = module {
    factory { (tag: String) -> featuresProvidersModules.getValue(tag) }
}