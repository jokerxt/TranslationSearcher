@file:Suppress("MemberVisibilityCanBePrivate", "unused", "SpellCheckingInspection")

import Version.kotlinVersion

private const val appCompatVersion = "1.2.0"
private const val coreKtxVersion = "1.3.1"
private const val fragmentKtxVersion = "1.2.5"
private const val materialVersion = "1.2.0"
private const val constraintLayoutVersion = "2.0.0"
private const val recyclerVersion = "1.1.0"
private const val recyclerHannesDorfmannVersion = "4.3.0"
private const val glideVersion = "4.11.0"
private const val ciceroneVersion = "5.1.1"
private const val koinVersion = "2.1.6"
private const val gsonVersion = "2.8.6"
private const val retrofitVersion = "2.9.0"
private const val retrofitRxAdapterVersion = "2.4.0"
private const val retrofitXmlConverterVersion = "2.3.0"
private const val rxJavaVersion = "2.2.19"
private const val rxKotlinVersion = "2.4.0"
private const val rxAndroidVersion = "2.1.1"
private const val rxBindingVersion = "2.2.0"
private const val roomVersion = "2.2.5"

private const val chuckVersion = "3.2.0"

private const val junitVersion = "4.13"
private const val mockitoVersion = "3.4.0"
private const val mockitoUtilVersion = "2.2.0"


object GradleLibraries {
    const val androidBuild = "com.android.tools.build:gradle:${Version.gradleVersion}"
    const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
}

object Libraries {
    // Support
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"
    const val appCompat = "androidx.appcompat:appcompat:$appCompatVersion"
    const val coreKtx = "androidx.core:core-ktx:$coreKtxVersion"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:$fragmentKtxVersion"
    
    // UI
    const val material = "com.google.android.material:material:$materialVersion"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"
    const val recycler = "androidx.recyclerview:recyclerview:$recyclerVersion"
    const val recyclerHannesDorfmann = "com.hannesdorfmann:adapterdelegates4-kotlin-dsl-layoutcontainer:$recyclerHannesDorfmannVersion"
    const val glide = "com.github.bumptech.glide:glide:$glideVersion"
    const val glideCompiler = "com.github.bumptech.glide:compiler:$glideVersion"
    
    // Navigation
    const val cicerone = "ru.terrakok.cicerone:cicerone:$ciceroneVersion"
    
    // DI
    const val koin = "org.koin:koin-androidx-scope:$koinVersion"
    const val koinViewModel = "org.koin:koin-androidx-viewmodel:$koinVersion"
    
    // Network
    const val gson = "com.google.code.gson:gson:$gsonVersion"
    const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    const val retrofitRxAdapter = "com.squareup.retrofit2:adapter-rxjava2:$retrofitRxAdapterVersion"
    const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
    
    // Rx
    const val rxJava = "io.reactivex.rxjava2:rxjava:$rxJavaVersion"
    const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:$rxKotlinVersion"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:$rxAndroidVersion"
    const val rxBinding = "com.jakewharton.rxbinding2:rxbinding-kotlin:$rxBindingVersion"
    const val rxBindingDesign = "com.jakewharton.rxbinding2:rxbinding-design-kotlin:$rxBindingVersion"
    
    // Database
    const val room = "androidx.room:room-runtime:$roomVersion"
    const val roomCompiler = "androidx.room:room-compiler:$roomVersion"
    const val roomRx = "androidx.room:room-rxjava2:$roomVersion"
}

object DebugLibraries {
    const val chuck = "com.github.chuckerteam.chucker:library:$chuckVersion"
    const val chuckRelease = "com.github.chuckerteam.chucker:library-no-op:$chuckVersion"
}

object TestLibraries {
    const val junit = "junit:junit:$junitVersion"

    // Mockito
    const val mockito = "org.mockito:mockito-core:$mockitoVersion"
    const val mockitoUtil = "com.nhaarman.mockitokotlin2:mockito-kotlin:$mockitoUtilVersion"
}