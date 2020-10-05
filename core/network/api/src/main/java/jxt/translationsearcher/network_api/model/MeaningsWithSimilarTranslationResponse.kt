package jxt.translationsearcher.network_api.model

import com.google.gson.annotations.SerializedName

data class MeaningsWithSimilarTranslationResponse(
    
    @SerializedName("meaningId")
    val meaningId: Int,
    
    @SerializedName("frequencyPercent")
    val frequencyPercent: Double,
    
    @SerializedName("partOfSpeechAbbreviation")
    val partOfSpeechAbbreviation: String,
    
    @SerializedName("translation")
    val translation: TranslationResponse

)