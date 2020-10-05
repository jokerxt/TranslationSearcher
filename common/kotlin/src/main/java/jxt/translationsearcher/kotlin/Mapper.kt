package jxt.translationsearcher.kotlin

interface Mapper<X, Y> {
    
    fun map(from: X): Y
    
    fun map(from: List<X>): List<Y> = from.map(::map)
    
}