package cz.home.nbaplayers.feature.playerdetails.domain

class GetPlayerDetailsUseCase(
    private val remotePlayerDetailsRepository: RemotePlayerDetailsRepository
) {

    suspend operator fun invoke(playerId: Int) =
        remotePlayerDetailsRepository.getPlayerById(playerId)
}