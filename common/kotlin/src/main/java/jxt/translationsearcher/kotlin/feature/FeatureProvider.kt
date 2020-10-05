package jxt.translationsearcher.kotlin.feature

fun interface FeatureProvider<T : Feature> {
    
    fun provide(): T
    
}