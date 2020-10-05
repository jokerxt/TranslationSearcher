package jxt.translationsearcher.searcher_impl.presentation

import io.reactivex.Observable
import jxt.translationsearcher.android.mvi.MviViewModel
import jxt.translationsearcher.kotlin.toObservable
import jxt.translationsearcher.searcher_impl.presentation.mvi.SearcherEffect as Effect
import jxt.translationsearcher.searcher_impl.presentation.mvi.SearcherEvent as Event
import jxt.translationsearcher.searcher_impl.presentation.mvi.SearcherViewState as ViewState
import jxt.translationsearcher.searcher_impl.presentation.mvi.SearcherWish as Wish

internal class SearcherViewModel(
        private val interactor: SearcherInteractor
) : MviViewModel<Wish, Effect, ViewState, Event>(ViewState()) {
    
    override fun act(state: ViewState, wish: Wish): Observable<out Effect> = when (wish) {
        is Wish.StartSearch -> Effect.SearchStarted(wish.query).toObservable()

        is Wish.EndSearch -> {
            interactor.findMeaning(wish.query)
                    .map<Effect>(Effect::SearchEnded)
                    .onErrorReturn(Effect::SearchError)
                    .toObservable()
        }
    }
    
    override fun reduce(oldState: ViewState, effect: Effect) = when (effect) {
        is Effect.SearchStarted -> oldState.copy(
                enteredText = effect.query,
                isSearchInProgress = true,
                isEmptyResults = false,
                isSearchError = false
        )

        is Effect.SearchEnded -> oldState.copy(
                isSearchInProgress = false,
                isEmptyResults = effect.words.isEmpty() && oldState.enteredText.isNotEmpty(),
                isSearchError = false,
                words = effect.words
        )

        is Effect.SearchError -> oldState.copy(
                isSearchInProgress = false,
                isSearchError = true
        )
    }
    
}