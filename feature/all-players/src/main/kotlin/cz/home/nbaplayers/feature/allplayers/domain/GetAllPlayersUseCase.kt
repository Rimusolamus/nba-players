package cz.home.nbaplayers.feature.allplayers.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import cz.home.nbaplayers.feature.allplayers.model.Player

internal class GetAllPlayersUseCase(
    private val remoteAllPlayersRepository: RemoteAllPlayersRepository
) : PagingSource<Int, Player>() {

    operator fun invoke(): PagingSource<Int, Player> {
        return this
    }

    override fun getRefreshKey(state: PagingState<Int, Player>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Player> {
        return try {
            val pageNumber = params.key ?: 0

            val response = remoteAllPlayersRepository.getAllPlayers(
                page = pageNumber * params.loadSize,
                perPage = params.loadSize
            )

            LoadResult.Page(
                data = response,
                prevKey = if (pageNumber > 0) pageNumber - 1 else null,
                nextKey = if (response.isNotEmpty()) pageNumber + 1 else null
            )

        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }
}