package jxt.translationsearcher.meaning_impl

import androidx.fragment.app.Fragment
import jxt.translationsearcher.meaning_api.MeaningFeature
import jxt.translationsearcher.meaning_impl.presentation.MeaningFragment
import org.koin.androidx.scope.lifecycleScope

internal class MeaningFeatureImpl(
    private val dependencies: MeaningFeatureDependencies
) : MeaningFeature {
    
    override fun getMeaningFragment(meaningId: Long): Fragment {
        return MeaningFragment.newInstance().apply {
            lifecycleScope.apply {
                declare(meaningId)
                declare(dependencies.meaningsApi)
            }
        }
    }
    
}