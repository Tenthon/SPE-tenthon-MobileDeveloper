package com.morbis.spe.di


import com.morbis.spe.data.local.SessionPref
import com.morbis.spe.data.remote.*
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val apiModule = module {
    single { OkHttpClientFactory.create(interceptors = arrayOf(AuthInterceptor()), showDebugLog = true) }
    single { ApiService.createService(ApiClient::class.java, get(), specialFun) }
}

val otherModule = module {
    single { SessionPref.instance(androidContext()) }
}