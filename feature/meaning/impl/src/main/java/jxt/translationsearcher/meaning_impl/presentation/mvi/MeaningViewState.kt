package jxt.translationsearcher.meaning_impl.presentation.mvi

data class MeaningViewState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val text: String = "",
    val translation: String = "",
    val imageUrl: String? = null
)