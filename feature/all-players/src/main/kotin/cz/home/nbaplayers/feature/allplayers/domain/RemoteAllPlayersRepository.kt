package cz.home.nbaplayers.feature.allplayers.domain

import cz.home.nbaplayers.library.data.infrastructure.LoadableData

interface RemoteAllPlayersRepository {

    suspend fun getAllPlayers() : Flow<LoadableData<List<Player>>>
}