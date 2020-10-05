package jxt.translationsearcher.network_api.model

import com.google.gson.annotations.SerializedName

data class AlternativeTranslationsResponse(
    
    @SerializedName("text")
    val text: String,
    
    @SerializedName("translation")
    val translation: TranslationResponse

)