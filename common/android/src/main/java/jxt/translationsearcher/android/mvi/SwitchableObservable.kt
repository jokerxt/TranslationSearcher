package jxt.translationsearcher.android.mvi

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

internal fun <T : Any> Observable<T>.switchSource(source: Observable<T>): Observable<T> {
    return if (this is SwitchableObservable<T>) {
        apply { switchSourceObservable(source) }
    } else {
        SwitchableObservable(source)
    }
}

internal class SwitchableObservable<T : Any> internal constructor(
    private var source: Observable<T>
) : Observable<T>() {
    
    private val disposable = CompositeDisposable()
    
    private var observer: Observer<in T>? = null
    
    override fun subscribeActual(observer: Observer<in T>?) {
        this.observer = observer
        updateSubscription()
    }
    
    fun dispose() {
        disposable.clear()
    }
    
    fun switchSourceObservable(source: Observable<T>) {
        this.source = source
        updateSubscription()
    }
    
    private fun updateSubscription() {
        dispose()
        if (observer == null) return
        
        disposable += source
            .subscribeOn(Schedulers.io())
            .subscribeBy { observer?.onNext(it) }
    }
    
}