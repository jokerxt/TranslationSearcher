package jxt.translationsearcher.searcher_impl.presentation.mvi

internal sealed class SearcherWish {
    
    class StartSearch(val query: String) : SearcherWish()
    
    class EndSearch(val query: String) : SearcherWish()
    
}