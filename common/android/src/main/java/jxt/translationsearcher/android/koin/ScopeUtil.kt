package jxt.translationsearcher.android.koin

import androidx.lifecycle.ViewModel
import org.koin.core.qualifier.TypeQualifier
import org.koin.core.scope.Scope
import org.koin.core.scope.ScopeCallback
import org.koin.ext.getFullName

inline fun Scope.whenScopeClose(crossinline onScopeClose: (Scope) -> Unit) {
    registerCallback(object : ScopeCallback {
        override fun onScopeClose(scope: Scope) {
            onScopeClose(scope)
        }
    })
}

fun Scope.mutualLink(otherScope: Scope) {
    this._linkedScope.add(0, otherScope)
    otherScope._linkedScope.add(this)
}

inline fun <reified VM : ViewModel> Scope.getOrCreateViewModelScope(idSuffix: String): Scope = getKoin().run {
    val scopeId = "holder." + VM::class.getFullName() + "@" + idSuffix
    val scopeName = TypeQualifier(VM::class)
    getScopeOrNull(scopeId) ?: createScope(scopeId, scopeName, this)
}

fun Scope.findLastHolderNonLinkedScopeByPartId(partOfId: String): Scope? {
    return getKoin()._scopeRegistry.scopes.entries.reversed().find {
        it.key.contains("holder.") && it.key.contains(partOfId) && it.value._linkedScope.size == 1
    }?.value
}