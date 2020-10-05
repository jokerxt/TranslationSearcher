package jxt.translationsearcher.meaning_impl.presentation

import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import jxt.translationsearcher.android.initLifecycleKoinModule
import jxt.translationsearcher.android.mvi.MviFragment
import jxt.translationsearcher.android.progressBarView
import jxt.translationsearcher.android_util.diffedText
import jxt.translationsearcher.meaning_impl.R
import jxt.translationsearcher.meaning_impl.di.meaningFeatureDynamicModule
import kotlinx.android.synthetic.main.meaning_fragment.*
import jxt.translationsearcher.meaning_impl.presentation.mvi.MeaningEvent as Event
import jxt.translationsearcher.meaning_impl.presentation.mvi.MeaningViewState as ViewState
import jxt.translationsearcher.meaning_impl.presentation.mvi.MeaningWish as Wish

internal class MeaningFragment : MviFragment<MeaningViewModel, Wish, ViewState, Event>(
    layoutId = R.layout.meaning_fragment,
    viewModelClazz = MeaningViewModel::class
) {
    
    init {
        initLifecycleKoinModule(meaningFeatureDynamicModule)
    }
    
    
    override fun render(state: ViewState) {
        text.diffedText = state.text
        translation.diffedText = state.translation
        
        loadingProgress.isVisible = state.isLoading
        errorLabel.isVisible = state.isError
        
        text.isGone = state.isLoading || state.isError
        translation.isGone = state.isLoading || state.isError
        
        updateImage(state)
    }
    
    private fun updateImage(state: ViewState) {
        state.imageUrl ?: return
        if (image.drawable != null) return
        
        Glide.with(image)
            .load("https:${state.imageUrl}")
            .progressBarView(loadingImageProgress)
            .error(ContextCompat.getDrawable(requireContext(), R.drawable.meaning_ic_load_failed))
            .into(image)
    }
    
    
    companion object {
        
        fun newInstance() = MeaningFragment()
        
    }
    
}