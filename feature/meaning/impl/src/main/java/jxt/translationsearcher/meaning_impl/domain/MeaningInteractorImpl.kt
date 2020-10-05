package jxt.translationsearcher.meaning_impl.domain

import io.reactivex.Single
import jxt.translationsearcher.meaning_impl.data.model.FullMeaning
import jxt.translationsearcher.meaning_impl.presentation.MeaningInteractor

internal class MeaningInteractorImpl(
    private val repository: MeaningRepository
) : MeaningInteractor {
    
    override fun getMeaning(meaningId: Long): Single<FullMeaning> {
        return repository.getMeaning(listOf(meaningId)).map { it.first() }
    }
    
}