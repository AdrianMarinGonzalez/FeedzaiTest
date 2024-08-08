package com.example.feedzaitest.di

import com.example.domain.GetDeviceInfoUseCase
import com.example.domain.GetGeolocationInfoUseCase
import com.example.domain.GetNetworkInfoUseCase
import com.example.domain.SendDataUseCase
import com.example.feedzaitest.InfoListLayoutModelMapper
import com.example.feedzaitest.InfoListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val modules = listOf(
    module {
        factory { GetNetworkInfoUseCase(get()) }
        factory { GetDeviceInfoUseCase(get()) }
        factory { GetGeolocationInfoUseCase(get()) }
        factory { SendDataUseCase(get()) }

        factory { InfoListLayoutModelMapper() }

        viewModel { InfoListViewModel(get(), get(), get(), get(), get()) }
    }
)