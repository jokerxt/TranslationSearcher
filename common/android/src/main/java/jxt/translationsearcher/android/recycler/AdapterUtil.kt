package jxt.translationsearcher.android.recycler

import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.AdapterDelegateLayoutContainerViewHolder
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateLayoutContainer
import jxt.translationsearcher.kotlin.model.KeyEntity

inline fun <reified T : KeyEntity<*>> createAdapter(
    @LayoutRes layout: Int,
    diffCallback: DiffUtil.ItemCallback<T> = DiffItemCallback(),
    noinline block: AdapterDelegateLayoutContainerViewHolder<T>.() -> Unit
): DifferDelegateAdapter<T> {
    return adapterDelegateLayoutContainer(layout = layout, block = block)
        .toDifferDelegateAdapter(diffCallback)
}

fun <T : KeyEntity<*>> AdapterDelegate<List<T>>.toDifferDelegateAdapter(
    diffCallback: DiffUtil.ItemCallback<T> = DiffItemCallback()
): DifferDelegateAdapter<T> {
    return DifferDelegateAdapter(this, diffCallback = diffCallback)
}

open class DifferDelegateAdapter<T : KeyEntity<*>>(
    vararg delegates: AdapterDelegate<List<T>>,
    diffCallback: DiffUtil.ItemCallback<T> = DiffItemCallback()
) : AsyncListDifferDelegationAdapter<T>(
    diffCallback,
    *delegates
)