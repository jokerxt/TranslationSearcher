package jxt.translationsearcher.searcher_impl

import jxt.translationsearcher.kotlin.feature.FeatureDependencies
import jxt.translationsearcher.network_api.WordsApi

class SearcherFeatureDependencies(val wordsApi: WordsApi) : FeatureDependencies