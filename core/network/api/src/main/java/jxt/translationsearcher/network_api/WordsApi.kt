package jxt.translationsearcher.network_api

import io.reactivex.Single
import jxt.translationsearcher.network_api.model.WordResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WordsApi {
    
    @GET("words/search?page=1&pageSize=30")
    fun findWords(@Query("search") query: String): Single<List<WordResponse>>
    
}