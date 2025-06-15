package cz.home.nbaplayers.feature.allplayers.di

import cz.home.nbaplayers.feature.allplayers.data.RetrofitAllPlayersRepository
import cz.home.nbaplayers.feature.allplayers.domain.GetAllPlayersUseCase
import cz.home.nbaplayers.feature.allplayers.domain.RemoteAllPlayersRepository
import cz.home.nbaplayers.feature.allplayers.presentation.AllPlayersViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val allPlayersModule = module {

    single<RemoteAllPlayersRepository> {
        RetrofitAllPlayersRepository(get(), get())
    }

    factoryOf(::GetAllPlayersUseCase)

    viewModelOf(::AllPlayersViewModel)
}