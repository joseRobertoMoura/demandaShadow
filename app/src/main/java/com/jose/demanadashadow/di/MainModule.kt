package com.jose.demanadashadow.di

import com.jose.demanadashadow.data.repository.DemandaShadowRepository
import com.jose.demanadashadow.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {

    factory {
        DemandaShadowRepository()
    }

    viewModel {
        MainViewModel(
            repository = get()
        )
    }
}