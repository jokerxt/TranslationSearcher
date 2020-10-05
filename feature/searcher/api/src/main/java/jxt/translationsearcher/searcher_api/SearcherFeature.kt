package jxt.translationsearcher.searcher_api

import androidx.fragment.app.Fragment
import jxt.translationsearcher.kotlin.feature.Feature

interface SearcherFeature : Feature {
    
    val searcherFragment: Fragment
    
}