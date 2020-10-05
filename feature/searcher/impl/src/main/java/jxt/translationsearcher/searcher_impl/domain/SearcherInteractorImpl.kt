package jxt.translationsearcher.searcher_impl.domain

import io.reactivex.Single
import jxt.translationsearcher.searcher_impl.data.model.Word
import jxt.translationsearcher.searcher_impl.presentation.SearcherInteractor

internal class SearcherInteractorImpl(
        private val repository: SearcherRepository
) : SearcherInteractor {
    
    override fun findMeaning(query: String): Single<List<Word>> {
        return repository.findWords(query)
    }
    
}