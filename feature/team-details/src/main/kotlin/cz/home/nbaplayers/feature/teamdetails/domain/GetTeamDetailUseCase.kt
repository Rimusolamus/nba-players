package cz.home.nbaplayers.feature.teamdetails.domain

internal class GetTeamDetailUseCase(
    private val remoteTeamDetailRepository: RemoteTeamDetailRepository
) {

    suspend operator fun invoke(teamId: Int) = remoteTeamDetailRepository.getTeamById(teamId)
}