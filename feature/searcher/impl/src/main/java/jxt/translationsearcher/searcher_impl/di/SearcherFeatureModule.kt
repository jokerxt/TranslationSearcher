package jxt.translationsearcher.searcher_impl.di

import jxt.translationsearcher.searcher_api.SearcherFeature
import jxt.translationsearcher.searcher_impl.SearcherFeatureDependencies
import jxt.translationsearcher.searcher_impl.SearcherFeatureImpl
import jxt.translationsearcher.searcher_impl.data.SearcherRepositoryImpl
import jxt.translationsearcher.searcher_impl.data.WordsMapper
import jxt.translationsearcher.searcher_impl.data.WordsMapperImpl
import jxt.translationsearcher.searcher_impl.domain.SearcherInteractorImpl
import jxt.translationsearcher.searcher_impl.domain.SearcherRepository
import jxt.translationsearcher.searcher_impl.presentation.SearcherFragment
import jxt.translationsearcher.searcher_impl.presentation.SearcherInteractor
import jxt.translationsearcher.searcher_impl.presentation.SearcherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searcherFeatureModule
    get() = module {
        factory<SearcherFeature> { (dependencies: SearcherFeatureDependencies) ->
            SearcherFeatureImpl(dependencies)
        }
    }

internal val searcherFeatureDynamicModule
    get() = module {
        scope<SearcherFragment> {
            viewModel { SearcherViewModel(interactor = get()) }
            scoped<SearcherInteractor> { SearcherInteractorImpl(repository = get()) }
            scoped<SearcherRepository> { SearcherRepositoryImpl(wordsApi = get(), wordsMapper = get()) }
            scoped<WordsMapper> { WordsMapperImpl() }
        }
    }