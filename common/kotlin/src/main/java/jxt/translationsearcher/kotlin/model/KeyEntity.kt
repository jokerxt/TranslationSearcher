package jxt.translationsearcher.kotlin.model

interface KeyEntity<Key> {
    
    val id: Key
    
    override fun equals(other: Any?): Boolean
    
}