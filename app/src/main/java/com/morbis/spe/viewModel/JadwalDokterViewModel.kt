package com.morbis.spe.viewModel

import androidx.lifecycle.MutableLiveData
import com.morbis.spe.base.BaseViewModel
import com.morbis.spe.base.Events
import com.morbis.spe.model.JadwalDokterItem
import com.morbis.spe.repository.JadwalDokterRepository

class JadwalDokterViewModel(private val repository: JadwalDokterRepository) :
    BaseViewModel() {

    val data = MutableLiveData<MutableList<JadwalDokterItem>>()

    fun getJadwal() {
        event.value = Events(isLoading = true,message = "Mengambil data...", isSuccess = null)
        launch {
            repository.getData{ isSuccess, messages, datas ->
                event.value = Events(isLoading = false, isSuccess = isSuccess, message = messages)
                if (datas != null) data.value = datas
            }
        }
    }
}