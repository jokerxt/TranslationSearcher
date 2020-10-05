package jxt.translationsearcher

import jxt.translationsearcher.meaning_api.MeaningFeatureProvider
import jxt.translationsearcher.meaning_impl.MeaningFeatureDependencies
import jxt.translationsearcher.searcher_api.SearcherFeatureProvider
import jxt.translationsearcher.searcher_impl.SearcherFeatureDependencies
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

internal val appModule = module {
    
    factory {
        SearcherFeatureProvider {
            get { parametersOf(SearcherFeatureDependencies(wordsApi = get())) }
        }
    }
    
    factory {
        MeaningFeatureProvider {
            get { parametersOf(MeaningFeatureDependencies(meaningsApi = get())) }
        }
    }
    
}