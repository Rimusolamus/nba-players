package cz.home.nbaplayers.feature.playerdetails.domain

import cz.home.nbaplayers.feature.playerdetails.model.Player
import cz.home.nbaplayers.library.data.infrastructure.LoadableData
import kotlinx.coroutines.flow.Flow

interface RemotePlayerDetailsRepository {

    suspend fun getPlayerById(playerId: Int): Flow<LoadableData<Player>>
}