package jxt.translationsearcher.network_api.model

import com.google.gson.annotations.SerializedName

data class WordResponse(
    
    @SerializedName("id")
    val id: Long,
    
    @SerializedName("text")
    val text: String,
    
    @SerializedName("meanings")
    val meanings: List<MeaningResponse>

)