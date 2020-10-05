package jxt.translationsearcher.meaning_api

import androidx.fragment.app.Fragment
import jxt.translationsearcher.kotlin.feature.Feature

interface MeaningFeature : Feature {
    
    fun getMeaningFragment(meaningId: Long): Fragment
    
}