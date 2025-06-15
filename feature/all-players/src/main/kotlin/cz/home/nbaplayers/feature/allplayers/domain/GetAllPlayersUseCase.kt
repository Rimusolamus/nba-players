package cz.home.nbaplayers.feature.allplayers.domain

internal class GetAllPlayersUseCase(
    private val remoteAllPlayersRepository: RemoteAllPlayersRepository
) {

    suspend operator fun invoke() = remoteAllPlayersRepository.getAllPlayers()
}