package cz.home.nbaplayers.feature.allplayers.domain

import cz.home.nbaplayers.feature.allplayers.model.Player

interface RemoteAllPlayersRepository {

    suspend fun getAllPlayers(
        page: Int,
        perPage: Int
    ): List<Player>
}