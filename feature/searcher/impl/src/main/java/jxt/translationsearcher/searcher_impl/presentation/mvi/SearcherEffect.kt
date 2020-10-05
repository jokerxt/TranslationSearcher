package jxt.translationsearcher.searcher_impl.presentation.mvi

import jxt.translationsearcher.searcher_impl.data.model.Word

internal sealed class SearcherEffect {
    
    class SearchStarted(val query: String) : SearcherEffect()
    
    class SearchEnded(val words: List<Word>) : SearcherEffect()
    
    class SearchError(val error: Throwable) : SearcherEffect()
    
}