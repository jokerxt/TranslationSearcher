package jxt.translationsearcher.android

import android.content.ComponentCallbacks
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import jxt.translationsearcher.android.koin.whenScopeClose
import jxt.translationsearcher.kotlin.contains
import org.koin.android.ext.android.get
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.getStateViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.Qualifier
import org.koin.java.KoinJavaComponent.getKoin
import kotlin.reflect.KClass

typealias FeaturesProvidersModules = Map<String, Module>

private val Module.firstOtherScopeQualifier: Qualifier?
    get() = otherScopes.firstOrNull()?.qualifier

private val Module.firstDefinitionTypeName: String?
    get() = rootScope.definitions.firstOrNull()?.primaryType?.simpleName

private val Module.isAlreadyLoaded: Boolean
    get() = getKoin()._modules.contains {
        val typeName = firstDefinitionTypeName
        val scopeQualifier = firstOtherScopeQualifier
        
        (typeName != null && it.firstDefinitionTypeName == typeName) ||
            (scopeQualifier != null && it.firstOtherScopeQualifier == scopeQualifier)
    }


fun <T : ViewModel> SavedStateRegistryOwner.scopedViewModel(
    viewModelClass: KClass<T>,
    parameters: ParametersDefinition = { parametersOf() }
) = lazy(LazyThreadSafetyMode.NONE) {
    lifecycleScope.run {
        getStateViewModel(owner = this@scopedViewModel, clazz = viewModelClass, parameters = parameters)
    }
}

fun LifecycleOwner.initLifecycleKoinModule(module: Module) {
    if (module.isAlreadyLoaded) return
    
    loadKoinModules(module)
    
    lifecycleScope.whenScopeClose {
        unloadKoinModules(module)
        // fix unload float-definitions
        val mutableDefinitions = getKoin()._scopeRegistry.scopeDefinitions as MutableMap
        module.otherScopes.map { it.qualifier.value }.forEach(mutableDefinitions::remove)
    }
}

inline fun <reified T : Any> ComponentCallbacks.initAndInject(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null
) = lazy(LazyThreadSafetyMode.NONE) {
    loadKoinModule(get { parametersOf(T::class.java.simpleName) })
    get<T>(qualifier, parameters)
}

fun loadKoinModule(module: Module) {
    if (!module.isAlreadyLoaded) loadKoinModules(module)
}