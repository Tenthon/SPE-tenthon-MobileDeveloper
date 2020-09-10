package com.morbis.spe.data.remote

import android.util.Base64
import androidx.annotation.NonNull
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit


object OkHttpClientFactory {
    private const val DEFAULT_MAX_REQUEST = 30

    fun create(interceptors: Array<Interceptor>, showDebugLog: Boolean): OkHttpClient {

        val builder = OkHttpClient.Builder()
                .readTimeout(120, TimeUnit.SECONDS)
                .connectTimeout(120, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)

        interceptors.forEach {
            builder.addInterceptor(it)
        }

        if (showDebugLog) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(interceptor).build()
        }
        val dispatcher = Dispatcher()
        dispatcher.maxRequests = DEFAULT_MAX_REQUEST
        builder.dispatcher(dispatcher)

        return builder.build()
    }
}


//morbisapi
val userFun by lazy {
    String(Base64.decode("bW9yYmlzYXBp", Base64.DEFAULT))
}
//morbis1234
val passFun by lazy {
    String(Base64.decode("bW9yYmlzMTIzNA==", Base64.DEFAULT))
}
val specialFun by lazy {
    String(Base64.decode("aHR0cDovLzM2LjkxLjM5LjExNS9saXZlLWNlay9hcGkvaW5kZXgucGhwL2FwcC8=", Base64.DEFAULT))
}
//Authorization
val typeFun by lazy {
    String(Base64.decode("QXV0aG9yaXphdGlvbg==", Base64.DEFAULT))
}

val baseImage by lazy {
    "http://36.91.39.115/morbis-IM2/"
}

val MULTIPART_FORM_DATA = "multipart/form-data"

fun createRequestBody(@NonNull s: String): RequestBody {
    return RequestBody.create(
        MediaType.parse(MULTIPART_FORM_DATA), s
    )
}