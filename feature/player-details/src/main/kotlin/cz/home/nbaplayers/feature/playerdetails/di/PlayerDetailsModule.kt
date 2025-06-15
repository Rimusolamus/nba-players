package cz.home.nbaplayers.feature.playerdetails.di

import cz.home.nbaplayers.feature.playerdetails.data.RetrofitPlayerDetailsRepository
import cz.home.nbaplayers.feature.playerdetails.domain.GetPlayerDetailsUseCase
import cz.home.nbaplayers.feature.playerdetails.domain.RemotePlayerDetailsRepository
import cz.home.nbaplayers.feature.playerdetails.presentation.PlayerDetailsViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val playerDetailsModule = module {
    single<RemotePlayerDetailsRepository> {
        RetrofitPlayerDetailsRepository(get(), get())
    }

    factoryOf(::GetPlayerDetailsUseCase)

    viewModelOf(::PlayerDetailsViewModel)
}