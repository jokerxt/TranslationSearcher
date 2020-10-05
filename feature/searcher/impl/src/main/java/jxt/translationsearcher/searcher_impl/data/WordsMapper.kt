package jxt.translationsearcher.searcher_impl.data

import jxt.translationsearcher.kotlin.Mapper
import jxt.translationsearcher.network_api.model.MeaningResponse
import jxt.translationsearcher.network_api.model.WordResponse
import jxt.translationsearcher.searcher_impl.data.model.Word

internal interface WordsMapper : Mapper<WordResponse, Word>

internal class WordsMapperImpl : WordsMapper {
    
    override fun map(from: WordResponse) = Word(
        id = from.id,
        text = from.text,
        meaningId = from.meanings.minOf(MeaningResponse::id)
    )
    
}