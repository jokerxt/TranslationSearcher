package jxt.translationsearcher.android.mvi

import android.os.Bundle
import androidx.annotation.CheckResult
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import jxt.translationsearcher.android.scopedViewModel
import org.koin.core.parameter.parametersOf
import kotlin.reflect.KClass

abstract class MviFragment<ViewModel : MviViewModel<Wish, *, State, Event>, Wish : Any, State : Any, Event : Any>(
    viewModelClazz: KClass<ViewModel>,
    private val initialWishes: List<Wish> = listOf(),
    @LayoutRes layoutId: Int
) : Fragment(layoutId) {
    
    protected val currentState: State?
        get() = _currentState
    
    private var _currentState: State? = null
    
    protected open val viewModelParameters by lazy { parametersOf() }
    
    private val viewModel by scopedViewModel(viewModelClazz, ::viewModelParameters)
    
    private val mviDisposable = CompositeDisposable()
    protected val globalDisposable = CompositeDisposable()
    
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel
    }
    
    override fun onStart() {
        super.onStart()
        
        mviDisposable.addAll(
            viewModel.state bindTo ::onStateReceived,
            viewModel.event bindTo ::onEventReceived
        )
        
        postInitialWishes(initialWishes)
    }
    
    override fun onStop() {
        super.onStop()
        mviDisposable.clear()
        globalDisposable.clear()
    }
    
    protected abstract fun render(state: State)
    
    protected open fun onEventReceived(event: Event) {}
    
    protected fun Wish.post() = viewModel.postWish(this)
    
    private fun postInitialWishes(wish: List<Wish>) = viewModel.postInitialWishes(wish)
    
    private fun onStateReceived(state: State) {
        _currentState = state
        render(state)
    }
    
    
    private companion object {
        
        @CheckResult
        private infix fun <T : Any> ObservableSource<T>.bindTo(consumer: (T) -> Unit): Disposable {
            return Observable.wrap(this)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeBy(onNext = consumer)
        }
        
    }
    
}