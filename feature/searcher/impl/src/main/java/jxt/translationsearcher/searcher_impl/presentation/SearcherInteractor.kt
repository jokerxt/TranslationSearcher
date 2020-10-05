package jxt.translationsearcher.searcher_impl.presentation

import io.reactivex.Single
import jxt.translationsearcher.searcher_impl.data.model.Word

internal interface SearcherInteractor {
    
    fun findMeaning(query: String): Single<List<Word>>
    
}