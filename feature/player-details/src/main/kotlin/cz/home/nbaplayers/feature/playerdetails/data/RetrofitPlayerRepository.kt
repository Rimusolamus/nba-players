package cz.home.nbaplayers.feature.playerdetails.data

import cz.home.nbaplayers.feature.playerdetails.domain.RemotePlayerDetailsRepository
import cz.home.nbaplayers.feature.playerdetails.model.Player
import cz.home.nbaplayers.library.data.infrastructure.LoadableData
import cz.home.nbaplayers.library.networking.data.AbstractApi
import cz.home.nbaplayers.library.networking.infrastructure.ApiService
import kotlinx.coroutines.flow.Flow

internal class RetrofitPlayerDetailsRepository(
    private val abstractApi: AbstractApi,
    private val allPlayersService: ApiService
): RemotePlayerDetailsRepository {

    override suspend fun getPlayerById(playerId: Int): Flow<LoadableData<Player>> {
        return abstractApi.request(
            fetchData = { allPlayersService.getPlayerById(playerId) },
            parseDto = { this.data.toModel() }
        )
    }
}