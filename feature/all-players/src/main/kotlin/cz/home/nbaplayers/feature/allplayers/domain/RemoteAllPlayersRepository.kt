package cz.home.nbaplayers.feature.allplayers.domain

import cz.home.nbaplayers.feature.allplayers.model.Player
import cz.home.nbaplayers.library.data.infrastructure.LoadableData
import kotlinx.coroutines.flow.Flow

interface RemoteAllPlayersRepository {

    suspend fun getAllPlayers() : Flow<LoadableData<List<Player>>>
}