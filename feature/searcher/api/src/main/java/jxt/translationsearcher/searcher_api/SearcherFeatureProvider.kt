package jxt.translationsearcher.searcher_api

import jxt.translationsearcher.kotlin.feature.FeatureProvider

fun interface SearcherFeatureProvider : FeatureProvider<SearcherFeature> {
    
    override fun provide(): SearcherFeature
    
}