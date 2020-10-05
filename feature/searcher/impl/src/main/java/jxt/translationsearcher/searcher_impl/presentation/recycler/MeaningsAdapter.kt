package jxt.translationsearcher.searcher_impl.presentation.recycler

import jxt.translationsearcher.android.recycler.createAdapter
import jxt.translationsearcher.searcher_impl.R
import jxt.translationsearcher.searcher_impl.data.model.Word
import kotlinx.android.synthetic.main.searcher_word_item.*

internal fun wordsAdapter(
        onItemCLicked: (Long) -> Unit
) = createAdapter<Word>(R.layout.searcher_word_item) {
    
    itemView.setOnClickListener {
        onItemCLicked.invoke(item.meaningId)
    }
    
    
    bind {
        word.text = item.text
    }
    
}