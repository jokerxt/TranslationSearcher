package jxt.translationsearcher.android.koin

import androidx.fragment.app.Fragment
import jxt.translationsearcher.android.base.LifecycleViewModel
import org.koin.androidx.scope.bindScope
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.ScopeDSL
import org.koin.ext.getOrCreateScope

inline fun <reified F : Fragment, reified VM : LifecycleViewModel> Module.linkedScope(
    noinline scopeSet: ScopeDSL.() -> Unit
) = scope<F> {
    viewModel {
        val viewModelScope = getOrCreateViewModelScope<VM>(id.substringAfter('@'))
        mutualLink(viewModelScope)
        whenScopeClose { fragmentScope -> viewModelScope.unlink(fragmentScope) }
        
        viewModelScope.get<VM> { it }.also { lifecycleViewModel ->
            // создается скоуп холдер и линкуется с viewModelScope
            // чтобы вызвав lifecycleScope во ViewModel можно было получить объекты из viewModelScope
            val scopeHolder = lifecycleViewModel.getOrCreateScope().apply {
                linkTo(viewModelScope)
                whenScopeClose { viewModelScope.close() }
            }
            
            lifecycleViewModel.bindScope(scopeHolder)
        }
    }
    
    scope<VM>(scopeSet)
}