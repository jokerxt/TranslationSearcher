package jxt.translationsearcher

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import jxt.translationsearcher.android.Navigator
import jxt.translationsearcher.meaning_api.MeaningFeatureProvider
import jxt.translationsearcher.searcher_api.SearcherFeatureProvider
import org.koin.android.ext.android.inject

class AppActivity : AppCompatActivity(), Navigator, FragmentManager.OnBackStackChangedListener {
    
    private val searcherFeatureProvider by inject<SearcherFeatureProvider>()
    private val meaningFeatureProvider by inject<MeaningFeatureProvider>()
    
    private val canBack: Boolean
        get() = supportFragmentManager.backStackEntryCount > 0
    
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportFragmentManager.addOnBackStackChangedListener(this)

        shouldDisplayHomeUp()

        if (savedInstanceState == null) {
            navigateToSearcher()
        }
    }
    
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> true.also {
            if (canBack) supportFragmentManager.popBackStack()
            else finish()
        }
        
        else -> super.onOptionsItemSelected(item)
    }
    
    override fun navigateToSearcher() {
        val searcherFeature = searcherFeatureProvider.provide()
        
        supportFragmentManager.commit {
            replace(R.id.appRootContainer, searcherFeature.searcherFragment)
        }
    }
    
    override fun navigateToMeaning(meaningId: Long) {
        val meaningFeature = meaningFeatureProvider.provide()
        
        supportFragmentManager.commit {
            replace(R.id.appRootContainer, meaningFeature.getMeaningFragment(meaningId))
            addToBackStack(null)
        }
    }
    
    override fun onBackStackChanged() {
        shouldDisplayHomeUp()
    }
    
    private fun shouldDisplayHomeUp() {
        supportActionBar?.setDisplayHomeAsUpEnabled(canBack)
    }
    
}