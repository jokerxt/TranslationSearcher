package jxt.translationsearcher.network_api

import io.reactivex.Single
import jxt.translationsearcher.network_api.model.FullMeaningResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MeaningsApi {
    
    @GET("meanings")
    fun getMeanings(@Query("ids") ids: String): Single<List<FullMeaningResponse>>
    
}