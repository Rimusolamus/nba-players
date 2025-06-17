package cz.home.nbaplayers.feature.allplayers.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import cz.home.nbaplayers.feature.allplayers.domain.GetAllPlayersUseCase
import cz.home.nbaplayers.feature.allplayers.model.Player
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal class AllPlayersViewModel(
    getAllPlayers: GetAllPlayersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(State())
    val uiState: StateFlow<State>
        get() = _uiState

    // normally I hold everything in state, but here I want to keep it simple
    val players: Flow<PagingData<Player>> = Pager(
        config = PagingConfig(
            pageSize = 35,
            enablePlaceholders = false,
        ),
        pagingSourceFactory = { getAllPlayers() }
    ).flow.cachedIn(viewModelScope)

    data class State(
        val isLoading: Boolean = false,
        val error: Throwable? = null
    )
}