package jxt.translationsearcher.meaning_impl.data

import jxt.translationsearcher.kotlin.Mapper
import jxt.translationsearcher.meaning_impl.data.model.FullMeaning
import jxt.translationsearcher.network_api.model.FullMeaningResponse

internal interface MeaningsMapper : Mapper<FullMeaningResponse, FullMeaning>

internal class MeaningsMapperImpl : MeaningsMapper {
    
    override fun map(from: FullMeaningResponse) = FullMeaning(
        id = from.id,
        text = from.text,
        translation = from.translation.text,
        imageUrl = from.images.firstOrNull()?.url
    )
    
}