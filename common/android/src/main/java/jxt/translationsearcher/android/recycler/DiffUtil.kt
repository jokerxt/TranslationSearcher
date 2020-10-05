package jxt.translationsearcher.android.recycler

import androidx.recyclerview.widget.DiffUtil
import jxt.translationsearcher.kotlin.model.KeyEntity

open class DiffItemCallback<T : KeyEntity<*>> : DiffUtil.ItemCallback<T>() {
    
    override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem.id == newItem.id
    
    override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
    
}