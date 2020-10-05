package jxt.translationsearcher.meaning_impl.domain

import io.reactivex.Single
import jxt.translationsearcher.meaning_impl.data.model.FullMeaning

internal interface MeaningRepository {
    
    fun getMeaning(meaningsIds: List<Long>): Single<List<FullMeaning>>
    
}