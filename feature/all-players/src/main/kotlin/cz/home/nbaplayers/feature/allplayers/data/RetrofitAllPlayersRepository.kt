package cz.home.nbaplayers.feature.allplayers.data

import cz.home.nbaplayers.feature.allplayers.domain.RemoteAllPlayersRepository
import cz.home.nbaplayers.library.networking.data.AbstractApi
import cz.home.nbaplayers.library.networking.infrastructure.ApiService
import cz.home.nbaplayers.feature.allplayers.model.Player
import cz.home.nbaplayers.library.data.infrastructure.Data
import kotlinx.coroutines.flow.firstOrNull

internal class RetrofitAllPlayersRepository(
    private val abstractApi: AbstractApi,
    private val allPlayersService: ApiService
) : RemoteAllPlayersRepository {

    override suspend fun getAllPlayers(
        page: Int,
        perPage: Int
    ): List<Player> {
        val dataAsFlow = abstractApi.request(
            fetchData = { allPlayersService.getPlayers(page, perPage) },
            parseDto = { this.data.map { it.toModel() } })

        val resultState = dataAsFlow.firstOrNull { it !is Data.Loading }

        return when (resultState) {
            is Data.Success -> resultState.value
            is Data.Error -> emptyList()
            is Data.Loading -> emptyList()
            null -> emptyList()
        }
    }
}