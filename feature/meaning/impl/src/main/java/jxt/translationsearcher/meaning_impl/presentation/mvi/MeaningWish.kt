package jxt.translationsearcher.meaning_impl.presentation.mvi

internal sealed class MeaningWish {
    
    class LoadMeaning(val meaningId: Long) : MeaningWish()
    
}