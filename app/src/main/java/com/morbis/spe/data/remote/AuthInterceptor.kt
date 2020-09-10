package com.morbis.spe.data.remote

import com.morbis.spe.data.local.SessionPref
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val authToken = Credentials.basic(userFun, passFun);
        var request = chain.request()
        val headers = request.headers().newBuilder().add(typeFun, authToken).build()
        request = request.newBuilder().headers(headers).build()
        return chain.proceed(request)
    }
}