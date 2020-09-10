package com.morbis.spe.di

import com.morbis.spe.repository.JadwalDokterRepository
import com.morbis.spe.viewModel.JadwalDokterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { JadwalDokterViewModel(get()) }

    single<JadwalDokterRepository>(createdAtStart = true) {
        JadwalDokterRepository.JadwalDokterRepositoryImpl(
            get()
        )
    }

}