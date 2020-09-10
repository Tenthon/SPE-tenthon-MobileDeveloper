package com.morbis.spe.repository

import com.morbis.spe.data.remote.ApiClient
import com.morbis.spe.model.JadwalDokterItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

interface JadwalDokterRepository {
    fun getData(
        onResult: (isSuccess: Boolean?, messages: String?, datas: MutableList<JadwalDokterItem>?) -> Unit
    ): Disposable

    class JadwalDokterRepositoryImpl(private val apiClient: ApiClient) :
        JadwalDokterRepository {
        override fun getData(
            onResult: (isSuccess: Boolean?, messages: String?, datas: MutableList<JadwalDokterItem>?) -> Unit
        ): Disposable =
            apiClient.jadwalDokter().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.isSuccessful) {
                        val resp = it.body()
                        if (resp?.status!!) {
                            onResult(true, "${resp.message}", resp.data?.toMutableList())
                        } else onResult(false, "${resp.message}", null)
                    } else onResult(false, it.message(), null)
                }, {
                    onResult(false, "${it.message}", null)
                })
    }
}