package jxt.translationsearcher.android.mvi

import androidx.lifecycle.LifecycleOwner
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import jxt.translationsearcher.android.base.LifecycleViewModel
import kotlin.reflect.KClass

abstract class MviViewModel<Wish : Any, Effect : Any, State : Any, Event : Any>(
    initialState: State
) : LifecycleViewModel(), LifecycleOwner {
    
    private var isInitialWishPosted = false
    
    val event: ObservableSource<Event>
        get() = eventSubject
    
    val state: ObservableSource<State>
        get() = stateSubject.distinctUntilChanged()
    
    private val wishesSubject = PublishSubject.create<Wish>()
    private val eventSubject = PublishSubject.create<Event>()
    private val stateSubject = BehaviorSubject.create<State>()
    
    private val flows = mutableMapOf<KClass<out Wish>, SwitchableObservable<*>>()
    
    private val mviDisposable = CompositeDisposable()
    
    
    init {
        stateSubject.onNext(initialState)
        
        mviDisposable += wishesSubject
            .flatWithLatestFrom(state, ::actWithSchedulers)
            .withLatestFrom(state, ::onEffectReceived)
            .subscribeBy(onNext = stateSubject::onNext)
    }
    
    
    override fun onCleared() {
        mviDisposable.clear()
        clearFlows()
        super.onCleared()
    }
    
    open fun onScreenChanged() {}
    
    
    protected fun Wish.post() = postWish(this)
    
    internal fun postInitialWishes(wish: List<Wish>) {
        if (!isInitialWishPosted) {
            wish.forEach(::postWish)
            isInitialWishPosted = true
        }
    }
    
    internal fun postWish(wish: Wish): Unit = wishesSubject.onNext(wish)
    
    
    protected open fun act(state: State, wish: Wish): Observable<out Effect> = Observable.empty()
    
    protected open fun reduce(oldState: State, effect: Effect): State = oldState
    
    @Suppress("UNCHECKED_CAST")
    protected open fun publishEvent(effect: Effect, state: State): Event? = null
    
    protected fun <T : Any> Observable<T>.asFlowSource(wishType: KClass<out Wish>): Observable<T> {
        val isFlowLaunched = flows.containsKey(wishType)
        
        if (!isFlowLaunched) {
            flows[wishType] = SwitchableObservable(this)
        }
        
        @Suppress("UNCHECKED_CAST")
        val flow = flows[wishType] as Observable<T>
        flow.switchSource(this)
        
        return if (isFlowLaunched) Observable.empty() else flow
    }
    
    
    private inline fun <T, U, R> Observable<T>.flatWithLatestFrom(
        other: ObservableSource<U>,
        crossinline combiner: (T, U) -> ObservableSource<out R>
    ): Observable<R> {
        val biFunction = BiFunction<T, U, ObservableSource<out R>> { t, u -> combiner.invoke(t, u) }
        return withLatestFrom(other, biFunction).flatMap { it }
    }
    
    private fun actWithSchedulers(wish: Wish, state: State): Observable<out Effect> {
        return act(state, wish)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    
    private fun onEffectReceived(effect: Effect, oldState: State): State {
        val newState = reduce(oldState, effect)
        val event = publishEvent(effect, newState)
        
        event?.let(eventSubject::onNext)
        return newState
    }
    
    private fun clearFlows() {
        flows.apply {
            forEach { it.value.dispose() }
            clear()
        }
    }
    
}