package jxt.translationsearcher.meaning_impl.presentation

import io.reactivex.Single
import jxt.translationsearcher.meaning_impl.data.model.FullMeaning

internal interface MeaningInteractor {
    
    fun getMeaning(meaningId: Long): Single<FullMeaning>
    
}