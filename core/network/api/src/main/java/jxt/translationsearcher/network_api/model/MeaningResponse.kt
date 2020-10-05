package jxt.translationsearcher.network_api.model

import com.google.gson.annotations.SerializedName

data class MeaningResponse(
    
    @SerializedName("id")
    val id: Long,
    
    @SerializedName("partOfSpeechCode")
    val partOfSpeechCode: String,
    
    @SerializedName("translation")
    val translation: TranslationResponse,
    
    @SerializedName("previewUrl")
    val previewUrl: String,
    
    @SerializedName("imageUrl")
    val imageUrl: String,
    
    @SerializedName("transcription")
    val transcription: String,
    
    @SerializedName("soundUrl")
    val soundUrl: String

)