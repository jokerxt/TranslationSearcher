package jxt.translationsearcher.searcher_impl

import androidx.fragment.app.Fragment
import jxt.translationsearcher.searcher_api.SearcherFeature
import jxt.translationsearcher.searcher_impl.presentation.SearcherFragment
import org.koin.androidx.scope.lifecycleScope

internal class SearcherFeatureImpl(
        private val dependencies: SearcherFeatureDependencies
) : SearcherFeature {
    
    override val searcherFragment: Fragment
        get() = SearcherFragment.newInstance().apply {
            lifecycleScope.apply {
                declare(dependencies.wordsApi)
            }
        }
    
}