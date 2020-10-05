package jxt.translationsearcher.searcher_impl.data

import io.reactivex.Single
import jxt.translationsearcher.network_api.WordsApi
import jxt.translationsearcher.searcher_impl.data.model.Word
import jxt.translationsearcher.searcher_impl.domain.SearcherRepository

internal class SearcherRepositoryImpl(
    private val wordsApi: WordsApi,
    private val wordsMapper: WordsMapper
) : SearcherRepository {
    
    override fun findWords(query: String): Single<List<Word>> {
        return wordsApi.findWords(query)
            .map(wordsMapper::map)
            .map { it.sortedBy(Word::id) }
    }
    
}