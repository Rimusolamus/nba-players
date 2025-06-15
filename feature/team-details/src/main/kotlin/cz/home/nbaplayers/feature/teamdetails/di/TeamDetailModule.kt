package cz.home.nbaplayers.feature.teamdetails.di

import cz.home.nbaplayers.feature.teamdetails.data.RetrofitTeamDetailRepository
import cz.home.nbaplayers.feature.teamdetails.domain.GetTeamDetailUseCase
import cz.home.nbaplayers.feature.teamdetails.domain.RemoteTeamDetailRepository
import cz.home.nbaplayers.feature.teamdetails.presentation.TeamDetailViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val teamDetailModule = module {
    factoryOf(::GetTeamDetailUseCase)

    single<RemoteTeamDetailRepository> { RetrofitTeamDetailRepository(get(), get()) }

    viewModelOf(::TeamDetailViewModel)
}