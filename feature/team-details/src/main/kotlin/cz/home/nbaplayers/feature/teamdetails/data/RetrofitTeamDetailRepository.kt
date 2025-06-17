package cz.home.nbaplayers.feature.teamdetails.data

import cz.home.nbaplayers.feature.teamdetails.domain.RemoteTeamDetailRepository
import cz.home.nbaplayers.feature.teamdetails.model.Team
import cz.home.nbaplayers.library.data.infrastructure.LoadableData
import cz.home.nbaplayers.library.networking.data.AbstractApi
import cz.home.nbaplayers.library.networking.infrastructure.ApiService
import kotlinx.coroutines.flow.Flow

internal class RetrofitTeamDetailRepository(
    private val abstractApi: AbstractApi,
    private val allPlayersService: ApiService
) : RemoteTeamDetailRepository {
    override suspend fun getTeamById(teamId: Int): Flow<LoadableData<Team>> {
        return abstractApi.request(
            fetchData = { allPlayersService.getTeamById(teamId) },
            parseDto = { this.data.toModel() }
        )
    }
}