package cz.home.nbaplayers.feature.allplayers.data

import cz.home.nbaplayers.feature.allplayers.domain.RemoteAllPlayersRepository
import cz.home.nbaplayers.library.data.infrastructure.LoadableData
import cz.home.nbaplayers.library.networking.data.AbstractApi
import cz.home.nbaplayers.library.networking.infrastructure.ApiService
import cz.home.nbaplayers.feature.allplayers.model.Player
import cz.home.nbaplayers.feature.allplayers.model.Team
import cz.home.nbaplayers.library.networking.model.AllPlayersDto
import kotlinx.coroutines.flow.Flow

internal class RetrofitAllPlayersRepository(
    private val abstractApi: AbstractApi,
    private val allPlayersService: ApiService
) : RemoteAllPlayersRepository {

    override suspend fun getAllPlayers(): Flow<LoadableData<List<Player>>> {
        return abstractApi.request(
            callApi = { allPlayersService.getPlayers() },
            parseDto = { this.data.map { it.toModel() } })
    }
}