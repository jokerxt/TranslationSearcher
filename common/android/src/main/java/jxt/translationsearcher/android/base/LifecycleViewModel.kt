package jxt.translationsearcher.android.base

import androidx.annotation.CallSuper
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.ViewModel

open class LifecycleViewModel : ViewModel(), LifecycleOwner {
    
    private val lifecycleRegistry by lazy(LazyThreadSafetyMode.NONE) { LifecycleRegistry(this) }
    
    
    init {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
    }
    
    
    override fun getLifecycle() = lifecycleRegistry
    
    @CallSuper
    override fun onCleared() {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    }
    
}