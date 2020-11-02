package jxt.translationsearcher.kotlin

inline fun <T> Iterable<T>.contains(predicate: (T) -> Boolean): Boolean = find(predicate) != null