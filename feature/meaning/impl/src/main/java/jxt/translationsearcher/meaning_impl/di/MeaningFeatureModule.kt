package jxt.translationsearcher.meaning_impl.di

import jxt.translationsearcher.android.koin.linkedScope
import jxt.translationsearcher.android.scopedViewModel
import jxt.translationsearcher.meaning_api.MeaningFeature
import jxt.translationsearcher.meaning_impl.MeaningFeatureDependencies
import jxt.translationsearcher.meaning_impl.MeaningFeatureImpl
import jxt.translationsearcher.meaning_impl.data.MeaningRepositoryImpl
import jxt.translationsearcher.meaning_impl.data.MeaningsMapper
import jxt.translationsearcher.meaning_impl.data.MeaningsMapperImpl
import jxt.translationsearcher.meaning_impl.domain.MeaningInteractorImpl
import jxt.translationsearcher.meaning_impl.domain.MeaningRepository
import jxt.translationsearcher.meaning_impl.presentation.MeaningFragment
import jxt.translationsearcher.meaning_impl.presentation.MeaningInteractor
import jxt.translationsearcher.meaning_impl.presentation.MeaningViewModel
import org.koin.dsl.module

val meaningFeatureModule = module {
    factory<MeaningFeature> { (dependencies: MeaningFeatureDependencies) ->
        MeaningFeatureImpl(dependencies)
    }
}

internal val meaningFeatureDynamicModule = module {
    linkedScope<MeaningFragment, MeaningViewModel> {
        scopedViewModel { MeaningViewModel(interactor = get(), meaningId = get()) }
        scoped<MeaningInteractor> { MeaningInteractorImpl(repository = get()) }
        scoped<MeaningRepository> { MeaningRepositoryImpl(meaningsApi = get(), meaningsMapper = get()) }
        scoped<MeaningsMapper> { MeaningsMapperImpl() }
    }
}