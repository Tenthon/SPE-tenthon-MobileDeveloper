package com.morbis.spe.data.remote

import com.morbis.spe.model.ResponseJadwalDokter
import io.reactivex.Flowable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {
    @GET("jadwaldokter")
    fun jadwalDokter(): Flowable<Response<ResponseJadwalDokter>>
}