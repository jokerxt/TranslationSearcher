package jxt.translationsearcher.searcher_impl.data

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import jxt.translationsearcher.network_api.WordsApi
import jxt.translationsearcher.network_api.model.MeaningResponse
import jxt.translationsearcher.network_api.model.TranslationResponse
import jxt.translationsearcher.network_api.model.WordResponse
import jxt.translationsearcher.searcher_impl.data.model.Word
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.net.SocketTimeoutException

internal class SearcherRepositoryImplTest {
    
    private val wordsApi = mock<WordsApi>()
    private val wordsMapper = mock<WordsMapper>()
    private val repository = SearcherRepositoryImpl(wordsApi = wordsApi, wordsMapper = wordsMapper)
    
    private val testMeaningResponse = MeaningResponse(
            id = 4444,
            partOfSpeechCode = "ph",
            translation = TranslationResponse(text = "привет", note = ""),
            previewUrl = "//",
            imageUrl = "//",
            transcription = "...",
            soundUrl = "//"
    )
    
    private val testWordResponse = WordResponse(
            id = 999,
            text = "hello",
            meanings = listOf(testMeaningResponse)
    )
    
    
    @After
    fun after() {
        RxJavaPlugins.reset()
        RxJavaPlugins.setErrorHandler { }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
    }
    
    @Before
    fun before() {
        RxJavaPlugins.reset()
        RxJavaPlugins.setErrorHandler { }
    }
    
    @Test
    fun `find word EXPECT success`() {
        val query = "привет"
        
        val mapperResultExpected = setupDependencies(query)
        
        repository.findWords(query)
            .test()
            .assertResult(mapperResultExpected)
    }
    
    @Test
    fun `find word EXPECT SocketTimeoutException`() {
        val query = "привет"
        
        setupDependencies(query)
        
        val wordsApiResultExpected = Observable.error<List<WordResponse>>(SocketTimeoutException())
            .singleOrError()
        
        whenever(wordsApi.findWords(query))
            .thenReturn(wordsApiResultExpected)
        
        repository.findWords(query)
            .test()
            .assertFailure(SocketTimeoutException::class.java)
    }
    
    private fun setupDependencies(query: String): List<Word> {
        val wordsResponses = listOf(testWordResponse)
        val wordsApiResultExpected = Observable.just(wordsResponses).singleOrError()
        
        val mapperResultExpected = listOf(
                Word(id = testWordResponse.id, text = testWordResponse.text, meaningId = testMeaningResponse.id)
        )
        
        whenever(wordsApi.findWords(query))
            .thenReturn(wordsApiResultExpected)
        
        whenever(wordsMapper.map(wordsResponses))
            .thenReturn(mapperResultExpected)
        
        return mapperResultExpected
    }
    
}