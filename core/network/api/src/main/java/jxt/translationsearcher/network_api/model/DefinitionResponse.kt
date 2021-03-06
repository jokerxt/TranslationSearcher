package jxt.translationsearcher.network_api.model

import com.google.gson.annotations.SerializedName

data class DefinitionResponse(
    
    @SerializedName("text")
    val text: String,
    
    @SerializedName("soundUrl")
    val soundUrl: String

)