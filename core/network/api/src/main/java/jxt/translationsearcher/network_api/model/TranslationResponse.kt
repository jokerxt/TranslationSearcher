package jxt.translationsearcher.network_api.model

import com.google.gson.annotations.SerializedName

data class TranslationResponse(
    
    @SerializedName("text")
    val text: String,
    
    @SerializedName("note")
    val note: String

)