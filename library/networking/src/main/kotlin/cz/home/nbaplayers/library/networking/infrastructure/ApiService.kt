package cz.home.nbaplayers.library.networking.infrastructure

import cz.home.nbaplayers.library.networking.model.AllPlayersDto
import cz.home.nbaplayers.library.networking.model.PlayerDto
import cz.home.nbaplayers.library.networking.model.TeamDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/v1/players")
    suspend fun getPlayers(): Response<AllPlayersDto>

    @GET("/v1/players/{id}")
    suspend fun getPlayerById(@Path("id") id: Int): Response<PlayerDto>

    @GET("/v1/teams/{id}")
    suspend fun getTeamById(@Path("id") id: Int): Response<TeamDto>
}