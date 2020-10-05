package jxt.translationsearcher.meaning_impl.presentation

import io.reactivex.Observable
import jxt.translationsearcher.android.mvi.MviViewModel
import jxt.translationsearcher.meaning_impl.presentation.mvi.MeaningEffect as Effect
import jxt.translationsearcher.meaning_impl.presentation.mvi.MeaningEvent as Event
import jxt.translationsearcher.meaning_impl.presentation.mvi.MeaningViewState as ViewState
import jxt.translationsearcher.meaning_impl.presentation.mvi.MeaningWish as Wish

internal class MeaningViewModel(
    private val interactor: MeaningInteractor,
    meaningId: Long
) : MviViewModel<Wish, Effect, ViewState, Event>(ViewState()) {
    
    
    init {
        Wish.LoadMeaning(meaningId).post()
    }
    
    
    override fun act(state: ViewState, wish: Wish): Observable<out Effect> = when (wish) {
        is Wish.LoadMeaning -> {
            interactor.getMeaning(wish.meaningId)
                .toObservable()
                .map<Effect>(Effect::LoadMeaningEnded)
                .startWith(Effect.LoadMeaningStarted)
                .onErrorReturn(Effect::LoadMeaningFailed)
        }
    }
    
    override fun reduce(oldState: ViewState, effect: Effect) = when (effect) {
        is Effect.LoadMeaningStarted -> oldState.copy(
            isLoading = true,
            isError = false
        )
    
        is Effect.LoadMeaningEnded -> oldState.copy(
            isLoading = false,
            text = effect.meaning.text,
            translation = effect.meaning.translation,
            imageUrl = effect.meaning.imageUrl
        )
    
        is Effect.LoadMeaningFailed -> oldState.copy(
            isLoading = false,
            isError = true
        )
    }
    
}