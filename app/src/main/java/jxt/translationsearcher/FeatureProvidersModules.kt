package jxt.translationsearcher

import jxt.translationsearcher.android.FeaturesProvidersModules
import jxt.translationsearcher.android.loadKoinModule
import jxt.translationsearcher.meaning_api.MeaningFeatureProvider
import jxt.translationsearcher.meaning_impl.MeaningFeatureDependencies
import jxt.translationsearcher.meaning_impl.di.meaningFeatureModule
import jxt.translationsearcher.searcher_api.SearcherFeatureProvider
import jxt.translationsearcher.searcher_impl.SearcherFeatureDependencies
import jxt.translationsearcher.searcher_impl.di.searcherFeatureModule
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

internal val featuresProvidersModules: FeaturesProvidersModules by lazy(LazyThreadSafetyMode.NONE) {
    mapOf(
        SearcherFeatureProvider::class.java.simpleName to searcherFeatureProviderModule,
        MeaningFeatureProvider::class.java.simpleName to meaningFeatureProviderModule
    )
}

internal val searcherFeatureProviderModule = module {
    single {
        SearcherFeatureProvider {
            loadKoinModule(searcherFeatureModule)
            get { parametersOf(SearcherFeatureDependencies(wordsApi = get())) }
        }
    }
}

internal val meaningFeatureProviderModule = module {
    single {
        MeaningFeatureProvider {
            loadKoinModule(meaningFeatureModule)
            get { parametersOf(MeaningFeatureDependencies(meaningsApi = get())) }
        }
    }
}