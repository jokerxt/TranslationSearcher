package jxt.translationsearcher.meaning_api

import jxt.translationsearcher.kotlin.feature.FeatureProvider

fun interface MeaningFeatureProvider : FeatureProvider<MeaningFeature> {
    
    override fun provide(): MeaningFeature
    
}