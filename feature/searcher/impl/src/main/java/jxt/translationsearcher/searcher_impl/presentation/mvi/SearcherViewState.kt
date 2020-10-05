package jxt.translationsearcher.searcher_impl.presentation.mvi

import jxt.translationsearcher.searcher_impl.data.model.Word

internal data class SearcherViewState(
        val isSearchInProgress: Boolean = false,
        val isEmptyResults: Boolean = false,
        val isSearchError: Boolean = false,

        val enteredText: String = "",
        val words: List<Word> = emptyList()
)