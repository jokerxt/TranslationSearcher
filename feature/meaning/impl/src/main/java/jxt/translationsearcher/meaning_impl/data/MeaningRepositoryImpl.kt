package jxt.translationsearcher.meaning_impl.data

import io.reactivex.Single
import jxt.translationsearcher.meaning_impl.data.model.FullMeaning
import jxt.translationsearcher.meaning_impl.domain.MeaningRepository
import jxt.translationsearcher.network_api.MeaningsApi

private const val IDS_DELIMITER = ","

internal class MeaningRepositoryImpl(
    private val meaningsApi: MeaningsApi,
    private val meaningsMapper: MeaningsMapper
) : MeaningRepository {
    
    override fun getMeaning(meaningsIds: List<Long>): Single<List<FullMeaning>> {
        return meaningsApi.getMeanings(meaningsIds.joinToString(IDS_DELIMITER))
            .map(meaningsMapper::map)
            .map { it.sortedBy(FullMeaning::id) }
    }
    
}