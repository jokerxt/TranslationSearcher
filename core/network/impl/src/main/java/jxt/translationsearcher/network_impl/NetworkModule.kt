package jxt.translationsearcher.network_impl

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import jxt.translationsearcher.network_api.MeaningsApi
import jxt.translationsearcher.network_api.WordsApi
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent.getKoin
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    
    fun getTunedRetrofit(baseUrl: String) = getKoin().run {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(get())
            .addConverterFactory(get<GsonConverterFactory>())
            .addCallAdapterFactory(get<RxJava2CallAdapterFactory>())
            .build()
    }
    
    
    single { get<Retrofit>().create(WordsApi::class.java) }
    single { get<Retrofit>().create(MeaningsApi::class.java) }
    
    single { getTunedRetrofit("${BuildConfig.BASE_URL}/${BuildConfig.VERISON_API}/") }
    single { Gson() }
    
    single {
        OkHttpClient.Builder()
            .readTimeout(BuildConfig.NETWORK_READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(BuildConfig.NETWORK_WRITE_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(BuildConfig.NETWORK_CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .apply {
                if (BuildConfig.DEBUG) addInterceptor(get<ChuckerInterceptor>())
            }.build()
    }
    
    single { GsonConverterFactory.create(get()) }
    single<RxJava2CallAdapterFactory> { RxJava2CallAdapterFactory.create() }
    single { ChuckerInterceptor(androidContext()) }
    
}