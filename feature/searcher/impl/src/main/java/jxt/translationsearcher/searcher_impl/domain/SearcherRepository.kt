package jxt.translationsearcher.searcher_impl.domain

import io.reactivex.Single
import jxt.translationsearcher.searcher_impl.data.model.Word

internal interface SearcherRepository {
    
    fun findWords(query: String): Single<List<Word>>
    
}