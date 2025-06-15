package cz.home.nbaplayers.feature.teamdetails.domain

import cz.home.nbaplayers.feature.teamdetails.model.Team
import cz.home.nbaplayers.library.data.infrastructure.LoadableData
import kotlinx.coroutines.flow.Flow

interface RemoteTeamDetailRepository {

    suspend fun getTeamById(teamId: Int): Flow<LoadableData<Team>>
}