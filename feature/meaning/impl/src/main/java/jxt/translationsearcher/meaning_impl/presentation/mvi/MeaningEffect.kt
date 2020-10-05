package jxt.translationsearcher.meaning_impl.presentation.mvi

import jxt.translationsearcher.meaning_impl.data.model.FullMeaning

internal sealed class MeaningEffect {
    
    object LoadMeaningStarted : MeaningEffect()
    
    class LoadMeaningEnded(val meaning: FullMeaning) : MeaningEffect()
    
    class LoadMeaningFailed(val error: Throwable) : MeaningEffect()
    
}