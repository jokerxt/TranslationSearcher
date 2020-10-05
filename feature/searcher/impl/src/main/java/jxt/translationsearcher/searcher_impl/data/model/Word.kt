package jxt.translationsearcher.searcher_impl.data.model

import jxt.translationsearcher.kotlin.model.KeyEntity

data class Word(
    override val id: Long,
    val text: String,
    val meaningId: Long
) : KeyEntity<Long>