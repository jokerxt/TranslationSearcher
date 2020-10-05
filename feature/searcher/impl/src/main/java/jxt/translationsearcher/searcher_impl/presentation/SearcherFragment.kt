package jxt.translationsearcher.searcher_impl.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.rxkotlin.plusAssign
import jxt.translationsearcher.android.Navigator
import jxt.translationsearcher.android.initLifecycleKoinModule
import jxt.translationsearcher.android.mvi.MviFragment
import jxt.translationsearcher.android.recycler.HorizontalDividerItemDecoration
import jxt.translationsearcher.android_util.diffedText
import jxt.translationsearcher.android_util.hideKeyboard
import jxt.translationsearcher.searcher_impl.R
import jxt.translationsearcher.searcher_impl.di.searcherFeatureDynamicModule
import jxt.translationsearcher.searcher_impl.presentation.recycler.wordsAdapter
import kotlinx.android.synthetic.main.searcher_fragment.*
import java.util.concurrent.TimeUnit
import jxt.translationsearcher.searcher_impl.presentation.mvi.SearcherEvent as Event
import jxt.translationsearcher.searcher_impl.presentation.mvi.SearcherViewState as ViewState
import jxt.translationsearcher.searcher_impl.presentation.mvi.SearcherWish as Wish

internal class SearcherFragment : MviFragment<SearcherViewModel, Wish, ViewState, Event>(
        layoutId = R.layout.searcher_fragment,
        viewModelClazz = SearcherViewModel::class
) {
    
    private val wordsAdapter = wordsAdapter(::onWordClick)
    
    
    init {
        initLifecycleKoinModule(searcherFeatureDynamicModule)
    }
    
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        meaningsList.adapter = wordsAdapter
        meaningsList.addItemDecoration(HorizontalDividerItemDecoration(context))
    }
    
    override fun onStart() {
        super.onStart()
        initSearchField()
    }
    
    override fun render(state: ViewState) {
        enteredWord.diffedText = state.enteredText
        emptyResultLabel.isVisible = state.isEmptyResults
        searchingProgress.isVisible = state.isSearchInProgress
        meaningsList.isGone = state.isSearchInProgress || state.isEmptyResults
        wordsAdapter.items = state.words
    }
    
    private fun initSearchField() {
        globalDisposable += observableSearchField()
            .subscribe { Wish.EndSearch(it).post() }
    }
    
    private fun observableSearchField(): Observable<String> {
        return RxTextView.textChangeEvents(enteredWord)
            .map { it.text().toString() }
            .distinctUntilChanged()
            .skip(1)
            .doOnNext { Wish.StartSearch(it).post() }
            .debounce(600, TimeUnit.MILLISECONDS)
    }
    
    private fun onWordClick(meaningId: Long) {
        hideKeyboard()
        (activity as? Navigator)?.navigateToMeaning(meaningId)
    }
    
    
    companion object {
        
        fun newInstance() = SearcherFragment()
        
    }
    
}