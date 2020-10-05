package jxt.translationsearcher.android

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import jxt.translationsearcher.android.base.LifecycleViewModel
import jxt.translationsearcher.android.koin.findLastHolderNonLinkedScopeByPartId
import jxt.translationsearcher.android.koin.mutualLink
import jxt.translationsearcher.android.koin.whenScopeClose
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.getStateViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.definition.BeanDefinition
import org.koin.core.definition.Definition
import org.koin.core.module.Module
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.Qualifier
import org.koin.dsl.ScopeDSL
import org.koin.java.KoinJavaComponent.getKoin
import kotlin.reflect.KClass

inline fun <reified T : LifecycleViewModel> ScopeDSL.scopedViewModel(
    qualifier: Qualifier? = null,
    override: Boolean = false,
    noinline definition: Definition<T>
): BeanDefinition<T> = scoped(qualifier, override, definition)

fun <T : ViewModel> SavedStateRegistryOwner.scopedViewModel(
    viewModelClass: KClass<T>,
    parameters: ParametersDefinition = { parametersOf() }
) = lazy(LazyThreadSafetyMode.NONE) {
    lifecycleScope.run {
        getStateViewModel(owner = this@scopedViewModel, clazz = viewModelClass, parameters = parameters).also {
            findLastHolderNonLinkedScopeByPartId(viewModelClass.java.simpleName)?.let { vmScope ->
                if (!_linkedScope.contains(vmScope)) {
                    mutualLink(vmScope)
                    whenScopeClose { vmScope.unlink(it) }
                }
            }
        }
    }
}

fun LifecycleOwner.initLifecycleKoinModule(module: Module) {
    loadKoinModules(module)
    
    lifecycleScope.whenScopeClose {
        unloadKoinModules(module)
        // fix unload float-definitions
        val mutableDefinitions = getKoin()._scopeRegistry.scopeDefinitions as MutableMap
        module.otherScopes.map { it.qualifier.value }.forEach(mutableDefinitions::remove)
    }
}