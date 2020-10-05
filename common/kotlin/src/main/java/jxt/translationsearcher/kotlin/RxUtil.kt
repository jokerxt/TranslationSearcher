package jxt.translationsearcher.kotlin

import io.reactivex.Observable

fun <T> T.toObservable(): Observable<T> = Observable.just(this)