package jxt.translationsearcher.network_api.model

import com.google.gson.annotations.SerializedName

data class FullMeaningResponse(
    
    @SerializedName("id")
    val id: Long,
    
    @SerializedName("wordId")
    val wordId: Long?,
    
    @SerializedName("difficultyLevel")
    val difficultyLevel: String,
    
    @SerializedName("partOfSpeechCode")
    val partOfSpeechCode: String,
    
    @SerializedName("prefix")
    val prefix: String,
    
    @SerializedName("text")
    val text: String,
    
    @SerializedName("soundUrl")
    val soundUrl: String,
    
    @SerializedName("transcription")
    val transcription: String,
    
    @SerializedName("properties")
    val properties: PropertiesResponse,
    
    @SerializedName("updatedAt")
    val updatedAt: String,
    
    @SerializedName("mnemonics")
    val mnemonics: String,
    
    @SerializedName("translation")
    val translation: TranslationResponse,
    
    @SerializedName("images")
    val images: List<ImageResponse>,
    
    @SerializedName("definition")
    val definition: DefinitionResponse,
    
    @SerializedName("examples")
    val examples: List<ExampleResponse>,
    
    @SerializedName("meaningsWithSimilarTranslation")
    val meaningsWithSimilarTranslation: List<MeaningsWithSimilarTranslationResponse>,
    
    @SerializedName("alternativeTranslations")
    val alternativeTranslations: List<AlternativeTranslationsResponse>

)