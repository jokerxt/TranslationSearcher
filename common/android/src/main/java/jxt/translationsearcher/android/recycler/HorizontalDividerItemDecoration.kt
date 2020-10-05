package jxt.translationsearcher.android.recycler

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import jxt.translationsearcher.android.R

class HorizontalDividerItemDecoration(
    context: Context?
) : DividerItemDecoration(context, VERTICAL) {
    
    init {
        context?.also {
            ContextCompat.getDrawable(context, R.drawable.horizontal_divider)?.also(::setDrawable)
        }
    }
    
}